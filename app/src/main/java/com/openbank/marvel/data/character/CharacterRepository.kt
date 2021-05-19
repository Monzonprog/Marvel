package com.openbank.marvel.data.character

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class CharacterRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val remote: CharacterRemoteDataSource,
) {
    fun getCharacters() = remote.getCharacters().flowOn(dispatcher)
    fun getCharacterDetail(id: Int) = remote.getCharacterDetail(id = id).flowOn(dispatcher)
}