package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.Book

class RemotedataSource(private val api_value: MifAPI) {
    suspend fun SearchBook(search: String?): List<Book> =
        api_value.getBooks(search = search)
    private fun Book.toDomain() = Book(
        id = id,
        title = title,
        authors = authors,
        book_annotation = book_annotation.orEmpty(),
        photo = photo.orEmpty(),
        genres = genres.orEmpty()
    )
}