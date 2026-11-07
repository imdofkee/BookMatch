package com.dofkee.bookme.data.mappers

import com.dofkee.bookme.data.local.dao.CollectionCount
import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.remote.dto.BookDto
import com.dofkee.bookme.domain.model.Collection
import com.dofkee.bookme.domain.model.CollectionType
import kotlin.collections.orEmpty

fun BookDto.toEntity(): BookEntity = BookEntity(
    id = idDto,
    title = titleDto,
    authors = authorsDto,
    annotation = annotationDto.orEmpty(),
    photo = imageDto,
    genres = genresDto.orEmpty()
)

fun CollectionCount.toDomain(firstBookPhoto: String? = null): com.dofkee.bookme.domain.model.Collection =
    Collection(
        type = CollectionType.valueOf(type),
        bookCount = count,
        firstBookPhoto = firstBookPhoto
    )