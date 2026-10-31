package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.BookDto

class RemoteDataSource(private val mifApi: MifAPI) {
    suspend fun getBooksFromAPI(search: String?) : List<BookDto> =
        mifApi.getBooks(search = search)

    fun searchBookById(id: Int) : BookDto =
        mifApi.getBookById(id = id)

    fun searchBookByTitle(search: String) : List<BookDto> =
        mifApi.getBookByTitle(query = search)
}