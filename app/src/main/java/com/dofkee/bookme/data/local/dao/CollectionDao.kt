package com.dofkee.bookme.data.local.dao
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dofkee.bookme.data.local.entity.BookToCollectionEntity
import kotlinx.coroutines.flow.Flow


data class CollectionCount(
    val type: String,
    val count: Int
)

@Dao
interface BookToCollectionDao {
    @Query("""
        SELECT type, COUNT(*) AS count
        FROM book_collections
        GROUP BY type
    """)
    fun observeCounts(): Flow<List<CollectionCount>>
    @Query("""
        SELECT books.photo FROM books
        INNER JOIN book_collections ON books.id = book_collections.book_id
        WHERE book_collections.type = :type
        ORDER BY book_collections.added_at DESC
        LIMIT 1
    """)
    suspend fun firstBookPhoto(type: String): String?
    @Query("""
        SELECT * FROM book_collections
        WHERE book_id = :bookId AND type = :type
    """)

    suspend fun get(bookId: Int, type: String): BookToCollectionEntity?
    @Upsert

    suspend fun upsert(link: BookToCollectionEntity)
    @Query("""
        DELETE FROM book_collections
        WHERE book_id = :bookId AND type = :type
    """)
    suspend fun remove(bookId: Int, type: String)
    @Query("""
        DELETE FROM book_collections
        WHERE book_id = :bookId AND type != :keepType
    """)
    suspend fun removeAllExcept(bookId: Int, keepType: String)

    @Query("DELETE FROM book_collections")
    suspend fun deleteAll()
}