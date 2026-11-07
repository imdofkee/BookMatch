package com.dofkee.bookme.data.local

import android.content.Context
import androidx.room.Room
import com.dofkee.bookme.data.local.AppDatabase
import com.dofkee.bookme.data.local.dao.BookDao
import com.dofkee.bookme.data.local.dao.BookToCollectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()   // на время
            .build()

    @Provides
    fun provideBookDao(db: AppDatabase): BookDao = db.bookDao()

    @Provides
    fun provideBookToCollectionDao(db: AppDatabase): BookToCollectionDao =
        db.bookToCollectionDao()
}