package com.openbank.marvel.data.character

import arrow.core.Either
import com.openbank.marvel.domain.MarvelCharacter
import com.openbank.marvel.domain.MarvelError
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getCharacters(): Flow<Either<MarvelError, List<MarvelCharacter>>>
}