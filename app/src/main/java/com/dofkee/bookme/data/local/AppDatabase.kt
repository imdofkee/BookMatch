package com.dofkee.bookme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dofkee.bookme.data.local.dao.BookDao
import com.dofkee.bookme.data.local.dao.BookToCollectionDao
import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.local.entity.BookToCollectionEntity

@Database(
    entities = [
        BookEntity::class,
        BookToCollectionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun bookToCollectionDao(): BookToCollectionDao
}

