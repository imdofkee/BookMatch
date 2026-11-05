package com.dofkee.bookme.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.local.entity.BookToCollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    /**
     * Все книги + информация о коллекции (если есть).
     *
     * LEFT JOIN: книга попадёт в результат, даже если не лежит ни в одной
     * коллекции (тогда collection_type = null).
     *
     * Возвращает Flow: Room сам перезапускает запрос, когда меняется
     * таблица books ИЛИ book_collections.
     */
    @Query("""
        SELECT books.*, book_collections.type AS collection_type
        FROM books
        LEFT JOIN book_collections ON books.id = book_collections.book_id
    """)
    fun observeAllWithCollection(): Flow<List<BookToCollectionEntity>>

    /**
     * Книги конкретной коллекции.
     *
     * INNER JOIN + WHERE type = ?: возвращает только те книги, у которых
     * есть связь с указанной коллекцией.
     * ORDER BY added_at DESC: сначала недавно добавленные.
     */
    @Query("""
        SELECT books.*, book_collections.type AS collection_type
        FROM books
        INNER JOIN book_collections ON books.id = book_collections.book_id
        WHERE book_collections.type = :type
        ORDER BY book_collections.added_at DESC
    """)
    fun observeByCollection(type: String): Flow<List<BookToCollectionEntity>>

    /** Одна книга по id (без информации о коллекции). */
    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getById(id: Int): BookEntity?

    /** Сколько всего книг в кэше — для проверки «нужно ли идти в сеть». */
    @Query("SELECT COUNT(*) FROM books")
    suspend fun count(): Int

    /**
     * Вставить или обновить книги.
     * @Upsert: если id есть — UPDATE, если нет — INSERT.
     */
    @Upsert
    suspend fun upsertAll(books: List<BookEntity>)

    /** Удалить книгу по id (связи удалятся каскадом). */
    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteById(id: Int)

    /** Удалить все книги (например, при logout). */
    @Query("DELETE FROM books")
    suspend fun deleteAll()
}