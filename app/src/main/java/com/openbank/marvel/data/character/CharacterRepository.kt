package com.openbank.marvel.data.character

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class CharacterRepository(private val remote: CharacterRemoteDataSource) {
    fun getCharacters() = remote.getCharacters().flowOn(Dispatchers.IO)
}