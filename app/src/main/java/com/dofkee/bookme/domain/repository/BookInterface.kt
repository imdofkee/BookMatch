package com.dofkee.bookme.domain.repository

import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.BookInCollection
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import kotlinx.coroutines.flow.Flow

interface BookRepository {
//    flow
    fun observeAll(): Flow<List<Book>>

    fun observeCollection(): Flow<List<Collection>>

    fun booksInCollection(collectionType: CollectionType): Flow<List<Book>>

    suspend fun addIntoCollection(bookId: Int, collectionType: CollectionType)

//    перемещение (откуда куда)
    suspend fun updateToCollection(bookId: Int, from: CollectionType, to: CollectionType)

    suspend fun removeIntoCollection(bookId: Int, collectionType: CollectionType)

    suspend fun searchBook(search: String): List<BookDto>

    suspend fun getAllBooksFromAPI(search: String) : List<BookDto>
}