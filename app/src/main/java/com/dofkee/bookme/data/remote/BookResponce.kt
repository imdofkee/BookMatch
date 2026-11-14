package com.dofkee.bookme.data.remote

import com.dofkee.bookme.data.remote.dto.BookDto
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("data")
    val books: List<BookDto>? = emptyList(),

    @SerializedName("meta")
    val meta: MetaDto? = null
)

data class MetaDto(
    @SerializedName("total") val total: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int
)