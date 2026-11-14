package com.dofkee.bookme.data.mappers

import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Book

fun BookDto.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title ?: "Без названия",
        // Разбиваем строку "Автор 1, Автор 2" на список
        authors = if (authors.isNullOrBlank()) emptyList()
        else authors.split(",").map { it.trim() },
        // В JSON нет описания, ставим заглушку
        annotation = "Описание отсутствует",
        // В JSON нет картинки, ставим null
        photo = null,
        // В JSON нет жанров, ставим пустой список
        genres = emptyList()
    )
}

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        authors = authors,
        bookAnnotation = annotation,
        photo = photo,
        genres = genres
    )
}