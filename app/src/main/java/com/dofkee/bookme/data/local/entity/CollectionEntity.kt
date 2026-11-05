package com.dofkee.bookme.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "book_collections",
    primaryKeys = ["book_id", "type"],
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["id"],
            childColumns = ["book_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("book_id"), Index("type")]
)
data class BookToCollectionEntity(
    @ColumnInfo(name = "book_id") val bookId: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "added_at") val addedAt: Long
)