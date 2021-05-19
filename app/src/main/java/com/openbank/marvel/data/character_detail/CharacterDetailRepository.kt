package com.openbank.marvel.data.character_detail

class CharacterDetailRepository(private val remote: CharacterDetailRemoteDataSource) {
    fun getCharacterDetail(id: Int) = remote.getCharacterDetail(id = id)
}