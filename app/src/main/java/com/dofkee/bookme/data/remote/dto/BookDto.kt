package com.dofkee.bookme.data.remote.dto

data class BookDto(
    var idDto: Int,
    var titleDto: String,
    var authorsDto: List<String>,
    var annotationDto: String?,
    var imageDto: String?,
    var genresDto: List<String>?
)