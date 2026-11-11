package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.data.remote.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MifAPI {
    // Эндпоинт на получение книг
    @GET("api/v1/books")
    suspend fun getBooks(
        @Query("search") search: String? = null,
        @Query("author") author: String? = null,
//        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): BooksResponse
    @GET("api/v1/books/{id}")
    suspend fun getBookById(@Path("id") id: Int): BookDto
    @GET("api/v1/books")
    suspend fun getBookByTitle(@Query("search") query: String): BooksResponse
}