package com.dofkee.bookme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("authors") val authors: String?, // Приходит строкой: "Автор 1, Автор 2"
    @SerializedName("url") val url: String?,
    @SerializedName("isbn") val isbn: String?
)