package com.dofkee.bookme.domain

enum class CollectionType {liked, later, disliked}

data class Collection(
    var Type: CollectionType,
    var collectionName: String,
    // сколько книг в коллекции
    var BookCount: Int,
    var FirstBookPic: String?,
)

//свзяь книги в БД и коллекции в которой она лежит
data class BookInCollection(
    var BookId: Int,
    var whichCollection: CollectionType,
)