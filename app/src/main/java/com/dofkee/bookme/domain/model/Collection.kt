package com.dofkee.bookme.domain.model

enum class CollectionType {liked, later, disliked}

data class Collection(
    val type: CollectionType,
    val name: String,
    val bookCount: Int,
    val firstBookPhoto: String?
)

//свзяь книги в БД и коллекции в которой она лежит
data class BookInCollection(
    var BookId: Int,
    var whichCollection: CollectionType,
)