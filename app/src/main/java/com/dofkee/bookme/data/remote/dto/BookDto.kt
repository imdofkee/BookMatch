package com.dofkee.bookme.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id") var idDto: Int,
    @SerializedName("title") var titleDto: String,
    @SerializedName("authors") var authorsDto: List<String>,
    @SerializedName("annotation") var annotationDto: String?,
    @SerializedName("image") var imageDto: String?,
    @SerializedName("genres") var genresDto: List<String>?
)

