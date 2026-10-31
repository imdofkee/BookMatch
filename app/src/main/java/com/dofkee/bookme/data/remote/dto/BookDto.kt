package com.dofkee.bookme.data.remote.dto

import android.icu.text.CaseMap
import com.dofkee.bookme.data.remote.MifAPI

import com.google.gson.annotations.SerializedName

data class BookDto(
//    var idDto: Int,
//    var titleDto: String,
//    var authorsDto: List<String>,
//    var annotationDto: String?,
//    var imageDto: String?,
//    var genresDto: List<String>?

    @SerializedName("id") val  id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("autrors") val authors: List<String>,
    @SerializedName("annotations") val annotations: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("genres") val genres: List<String>
)
