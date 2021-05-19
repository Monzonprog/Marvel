package com.openbank.marvel.data.character_detail

import arrow.core.Either
import com.openbank.marvel.domain.MarvelCharacterDetail
import com.openbank.marvel.domain.MarvelError
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRemoteDataSource {
    fun getCharacterDetail(id: Int): Flow<Either<MarvelError, MarvelCharacterDetail>>
}