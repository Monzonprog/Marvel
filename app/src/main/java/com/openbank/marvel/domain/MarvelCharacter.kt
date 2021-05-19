package com.openbank.marvel.domain

data class MarvelCharacter(
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val thumbnailPath: String = "",
    val comics: List<String> = emptyList(),
    val series: List<String> = emptyList(),
)