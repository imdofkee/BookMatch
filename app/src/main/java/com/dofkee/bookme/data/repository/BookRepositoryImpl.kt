package com.dofkee.bookme.data.repository

import android.util.Log
import com.dofkee.bookme.data.remote.RemoteDataSource
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.BookInCollection
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import com.dofkee.bookme.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
//    private val dao: BookDao,
    private val inCollection: BookInCollection,
    private val dto: BookDto,
    private val remote: RemoteDataSource,

    ) : BookRepository {

    override fun observeAll(): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override fun observeCollection(): Flow<List<Collection>> {
        TODO("Not yet implemented")
    }

    override fun booksInCollection(collectionType: CollectionType): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun addIntoCollection(bookId: Int, collectionType: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun updateToCollection(bookId: Int, from: CollectionType, to: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun removeIntoCollection(bookId: Int, collectionType: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun searchBook(search: String): List<BookDto> {
        try {
            return remote.searchBookByTitle(search)
        }
        catch (e: Exception) {
            throw e
        }
    }

    suspend fun getBooksFromAPI(search: String) : List<BookDto> {
        try {
            return remote.getBooksFromAPI(search)
        }
        catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getAllBooksFromAPI(search: String) : List<BookDto> {
        try {
            return remote.getBooksFromAPI(search)
        } catch (e: Exception) {
            Log.e("BookRepository", "getBooksFromAPI failed", e)
            throw e
        }
    }

}