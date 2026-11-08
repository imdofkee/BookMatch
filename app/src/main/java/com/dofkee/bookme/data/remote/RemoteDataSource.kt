package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.BookDto

class RemoteDataSource(private val mifApi: MifAPI) {

    suspend fun getBooksFromAPI(search: String?): List<BookDto> {
        val response = mifApi.getBooks(search = search)
        return response.books ?: emptyList()
    }

    suspend fun searchBookById(id: Int): BookDto =
        mifApi.getBookById(id = id)

    suspend fun searchBookByTitle(search: String): List<BookDto> {
        val response = mifApi.getBookByTitle(query = search)
        return response.books ?: emptyList()
    }
}