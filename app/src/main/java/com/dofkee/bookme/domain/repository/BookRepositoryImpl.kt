package com.dofkee.bookme.domain.repository

import com.dofkee.bookme.data.remote.RemotedataSource
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.BookInCollection
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
//    private val dao: BookDao,
//    private val inCollection: BookInCollection,
//    private val dto: BookDto,
    private val remote: RemotedataSource,

) : BookRepository {

    override fun observeAll(): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(id: Int): Book? {
        TODO("Not yet implemented")
    }

    override fun observeCollection(): Flow<List<Collection>> {
        TODO("Not yet implemented")
    }

    override fun booksInCollection(collectionType: CollectionType): Flow<List<BookInCollection>> {
        TODO("Not yet implemented")
    }

    override suspend fun addIntoCollection(BookId: Int, collectionType: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun updateToCollection(BookId: Int, from: CollectionType, to: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun removeIntoCollection(BookId: Int, collectionType: CollectionType) {
        TODO("Not yet implemented")
    }

    override suspend fun searchBook(search: String): List<BookDto> {
        try {
            return remote.searchBook(search)
        }
        catch (e: Exception) {
            throw e
        }
    }


}