package com.dofkee.bookme.data.repository

import android.util.Log
import com.dofkee.bookme.data.local.dao.BookDao
import com.dofkee.bookme.data.local.dao.BookToCollectionDao
import com.dofkee.bookme.data.local.entity.BookToCollectionEntity
import com.dofkee.bookme.data.mappers.toDomain
import com.dofkee.bookme.data.mappers.toEntity
import com.dofkee.bookme.data.remote.RemoteDataSource
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import com.dofkee.bookme.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val dao: BookDao,
    private val collectionDao: BookToCollectionDao,
    private val remote: RemoteDataSource,
) : BookRepository {

    override fun observeAll(): Flow<List<Book>> =
        dao.observeAllWithCollection().map { rows ->
            // книги, ещё не отправленные ни в одну коллекцию (для свайп-ленты)
            rows.filter { it.collectionType == null }.map { it.book.toDomain() }
        }

    override fun observeCollection(): Flow<List<Collection>> =
        collectionDao.observeCounts().map { counts ->
            counts.map { count ->
                // Получаем картинку первой книги для обложки коллекции (suspend функция внутри map)
                val photo = collectionDao.firstBookPhoto(count.type)

                // Преобразуем строку в enum
                val collectionType = when (count.type.lowercase()) {
                    "liked" -> CollectionType.liked
                    "later" -> CollectionType.later
                    "disliked" -> CollectionType.disliked
                    else -> CollectionType.liked // на всякий случай
                }

                // Создаем объект Collection напрямую
                Collection(
                    type = collectionType,
                    name = when (collectionType) {
                        CollectionType.liked -> "Понравилось"
                        CollectionType.later -> "На потом"
                        CollectionType.disliked -> "Не понравилось"
                    },
                    bookCount = count.count,
                    firstBookPhoto = photo
                )
            }
        }

    override fun booksInCollection(collectionType: CollectionType): Flow<List<Book>> =
        dao.observeByCollection(collectionType.name).map { rows ->
            rows.map { it.book.toDomain() }
        }

    override suspend fun addIntoCollection(bookId: Int, collectionType: CollectionType) {
        collectionDao.upsert(
            BookToCollectionEntity(
                bookId = bookId,
                type = collectionType.name,
                addedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun updateToCollection(bookId: Int, from: CollectionType, to: CollectionType) {
        collectionDao.remove(bookId, from.name)
        collectionDao.upsert(
            BookToCollectionEntity(
                bookId = bookId,
                type = to.name,
                addedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun removeIntoCollection(bookId: Int, collectionType: CollectionType) {
        collectionDao.remove(bookId, collectionType.name)
    }

    override suspend fun searchBook(search: String): List<BookDto> {
        return remote.searchBookByTitle(search)
    }

    override suspend fun getAllBooksFromAPI(search: String): List<BookDto> {
        try {
            Log.wtf("BOOKME_DIAG", "remote.getBooksFromAPI('$search') started")
            val books = remote.getBooksFromAPI(search)
            Log.wtf("BOOKME_DIAG", "remote returned ${books.size} items")
            val entities = books.map { it.toEntity() }
            dao.upsertAll(entities)
            Log.wtf("BOOKME_DIAG", "upserted ${entities.size} rows into Room")
            return books
        } catch (e: Exception) {
            Log.wtf("BOOKME_DIAG", "repo error: ${e.message}", e)
            throw e
        }
    }
}