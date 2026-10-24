package com.dofkee.bookme.data.remote.dto

data class BookDto(
    val id: Int,
    val mif_id: Int,
//    пока что не нужно
//    val category_id: Int,
    val title: String,
//    хз че это
//    val isbn: String,
//    молимся что у книги всего 1 автор
    val authors: String,
//    отсюда в теории можно подтянуть обложку книги
    val annotation: String?,
    val url_to: String,
    val genres: List<String>? = emptyList()
)