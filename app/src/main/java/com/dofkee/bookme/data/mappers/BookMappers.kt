package com.dofkee.bookme.data.mappers

import com.dofkee.bookme.data.local.dao.CollectionCount
import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType

fun BookDto.toEntity(): BookEntity = BookEntity(
    id = idDto,
    title = titleDto,
    authors = authorsDto,
    annotation = annotationDto.orEmpty(),
    photo = imageDto,
    genres = genresDto.orEmpty()
)

fun CollectionCount.toDomain(firstBookPhoto: String? = null): Collection =
    Collection(
        type = CollectionType.valueOf(type),
        name = when (CollectionType.valueOf(type)) {
            CollectionType.liked    -> "Понравившееся"
            CollectionType.later    -> "Позже"
            CollectionType.disliked -> "Отклонённые"
        },
        bookCount = count,
        firstBookPhoto = firstBookPhoto
    )