package com.dofkee.bookme.data.local.dao
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dofkee.bookme.data.local.entity.BookToCollectionEntity
import kotlinx.coroutines.flow.Flow

/**
 * Результат GROUP BY type + COUNT(*).
 * Используется в BookToCollectionDao.observeCounts().
 */
data class CollectionCount(
    val type: String,
    val count: Int
)


@Dao
interface BookToCollectionDao {

    /**
     * Сколько книг в каждой коллекции.
     * GROUP BY type + COUNT(*): для экрана «Мои коллекции».
     *
     * Возвращает Flow: обновляется, когда меняется book_collections.
     */
    @Query("""
        SELECT type, COUNT(*) AS count
        FROM book_collections
        GROUP BY type
    """)
    fun observeCounts(): Flow<List<CollectionCount>>

    /**
     * Фото первой (недавно добавленной) книги в коллекции.
     * Для обложки карточки коллекции на экране «Мои коллекции».
     * Возвращает null, если коллекция пуста.
     */
    @Query("""
        SELECT books.photo FROM books
        INNER JOIN book_collections ON books.id = book_collections.book_id
        WHERE book_collections.type = :type
        ORDER BY book_collections.added_at DESC
        LIMIT 1
    """)
    suspend fun firstBookPhoto(type: String): String?

    /** Есть ли связь «книга ↔ коллекция». */
    @Query("""
        SELECT * FROM book_collections
        WHERE book_id = :bookId AND type = :type
    """)
    suspend fun get(bookId: Int, type: String): BookToCollectionEntity?

    /** Добавить/обновить связь. */
    @Upsert
    suspend fun upsert(link: BookToCollectionEntity)

    /** Удалить книгу из коллекции. */
    @Query("""
        DELETE FROM book_collections
        WHERE book_id = :bookId AND type = :type
    """)
    suspend fun remove(bookId: Int, type: String)

    /**
     * Удалить книгу из ВСЕХ коллекций, кроме указанной.
     * Нужно, если коллекции взаимоисключающие (лайк / позже / дизлайк):
     * при добавлении в LIKED книга должна уйти из LATER и DISLIKED.
     */
    @Query("""
        DELETE FROM book_collections
        WHERE book_id = :bookId AND type != :keepType
    """)
    suspend fun removeAllExcept(bookId: Int, keepType: String)

    /** Полностью очистить связи (например, при logout). */
    @Query("DELETE FROM book_collections")
    suspend fun deleteAll()
}