package com.openbank.marvel.framework.marvel

data class MarvelResponse(
    val code: Int = -1,
    val status: String = "",
    val data: MarvelData = MarvelData(),
)

data class MarvelData(
    val results: List<MarvelCharacter> = emptyList(),
)

data class MarvelCharacter(
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val thumbnail: MarvelThumbnail = MarvelThumbnail(),
    val comics: MarvelComponent = MarvelComponent(),
    val series: MarvelComponent = MarvelComponent(),
)

data class MarvelThumbnail(
    val path: String = "",
    val extension: String = "",
)

data class MarvelComponent(
    val items: List<MarvelItem> = emptyList(),
)

data class MarvelItem(
    val name: String = "",
)