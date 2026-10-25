package com.dofkee.bookme.domain.repository

import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.BookInCollection
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import kotlinx.coroutines.flow.Flow

interface BookRepository {
//    flow
    fun observeAll(): Flow<List<Book>>
// by one
    suspend fun getBook(id: Int): Book?

// обновление ленты вручную (пока я не уверена, что надо)
//    suspend fun refresh()

    fun observeCollection(): Flow<List<Collection>>

    fun booksInCollection(collectionType: CollectionType): Flow<List<BookInCollection>>

    suspend fun addIntoCollection(BookId: Int, collectionType: CollectionType)

//    перемещение (откуда куда)
    suspend fun updateToCollection(BookId: Int, from: CollectionType, to: CollectionType)

    suspend fun remveIntoCollection(BookId: Int, collectionType: CollectionType)
}