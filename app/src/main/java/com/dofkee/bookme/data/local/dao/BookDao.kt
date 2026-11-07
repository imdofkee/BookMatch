package com.dofkee.bookme.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.local.entity.BookWithCollection
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("""
        SELECT books.*, book_collections.type AS collection_type
        FROM books
        LEFT JOIN book_collections ON books.id = book_collections.book_id
    """)
    fun observeAllWithCollection(): Flow<List<BookWithCollection>>
    //                                      ^^^^^^^^^^^^^^^^ ✅ BookWithCollection

    @Query("""
        SELECT books.*, book_collections.type AS collection_type
        FROM books
        INNER JOIN book_collections ON books.id = book_collections.book_id
        WHERE book_collections.type = :type
        ORDER BY book_collections.added_at DESC
    """)
    fun observeByCollection(type: String): Flow<List<BookWithCollection>>
    //                                           ^^^^^^^^^^^^^^^^ ✅ BookWithCollection

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getById(id: Int): BookEntity?

    @Query("SELECT COUNT(*) FROM books")
    suspend fun count(): Int

    @Upsert
    suspend fun upsertAll(books: List<BookEntity>)

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}