package com.dofkee.bookme.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BookWithCollection(
    @Embedded val book: BookEntity,
    @ColumnInfo(name = "collection_type") val collectionType: String?
)