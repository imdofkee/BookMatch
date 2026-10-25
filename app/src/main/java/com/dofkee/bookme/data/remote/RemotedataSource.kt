package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book

class RemotedataSource(private val api_value: MifAPI) {
    suspend fun searchBook(search: String?) : List<BookDto> =
        api_value.getBooks(search = search)
    public fun BookDto.toDomain() = Book(
        id = idDto,
        title = titleDto,
        authors = authorsDto,
        bookAnnotation = annotationDto.orEmpty(),
        photo = imageDto.orEmpty(),
        genres = genresDto.orEmpty()
    )
}