package com.dofkee.bookme.domain.model

//дата класс книги в рамках бизнес-логики
data class Book (
    var id: Int,
    var title: String,
    var authors: List<String>,
    var bookAnnotation: String,
    var photo: String?,
    var genres: List<String>
)