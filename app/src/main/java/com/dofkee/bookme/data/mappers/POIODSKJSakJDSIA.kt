package com.dofkee.bookme.data.mappers

import com.dofkee.bookme.data.local.entity.BookEntity
import com.dofkee.bookme.data.remote.dto.BookDto
import kotlin.collections.orEmpty

fun BookDto.toEntity() = BookEntity(
    idEntity = idDto,
    titleEntity = titleDto,
    authorsEntity = authorsDto,
    annotationEntity = annotationDto.orEmpty(),
    imageEntity = imageDto.orEmpty(),
    genresEntity = genresDto.orEmpty()
)