package com.openbank.marvel.usecase.character

import com.openbank.marvel.data.character.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class GetCharacters(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val repository: CharacterRepository,
) {
    operator fun invoke() = repository.getCharacters().flowOn(dispatcher)
}