package com.dofkee.bookme.data.remote

import android.icu.text.StringSearch
import com.dofkee.bookme.data.remote.dto.BookDto

//импорты для апи
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MifAPI {
//    эндпоинт из АПи на получение книг
    @GET("api/v1/books")
    suspend fun getBooks(
        @Query("search") search: String? = null,
        @Query("author") author: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): List<BookDto>
    @GET("api/v1/books/{id}")
    suspend fun getBookById(@Path("id") id: Int): BookDto
}