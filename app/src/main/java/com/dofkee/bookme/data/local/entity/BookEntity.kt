package com.dofkee.bookme.data.local.entity

data class BookEntity(
    var idEntity: Int,
    var titleEntity: String,
    var authorsEntity: List<String>,
    var annotationEntity: String?,
    var imageEntity: String?,
    var genresEntity: List<String>?
)