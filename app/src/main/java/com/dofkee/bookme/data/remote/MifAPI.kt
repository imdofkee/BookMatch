package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.Book

//импорты для апи
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MifAPI {
//    эндпоинт из АПИ на получение книг
    @GET("api/v1/books")
    suspend fun getBooks(
        @Query("search") search: String? = null,
        @Query("author") author: String? = null,
        @Query("limit") limit: Int = 10,
//        двигать offset!
        @Query("offset") offset: Int = 10
    ): List<Book>
    @GET("api/v1/books/{id}")
    suspend fun getBookById(@Path("id") id: Int): Book
}