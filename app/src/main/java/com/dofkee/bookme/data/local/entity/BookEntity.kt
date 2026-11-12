package com.dofkee.bookme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val authors: List<String>,
    val annotation: String,
    val photo: String?,
    val genres: List<String>
)