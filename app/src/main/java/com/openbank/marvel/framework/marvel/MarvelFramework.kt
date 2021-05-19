package com.openbank.marvel.framework.marvel

import arrow.core.Either
import arrow.core.left
import com.openbank.marvel.data.character.CharacterRemoteDataSource
import com.openbank.marvel.data.character_detail.CharacterDetailRemoteDataSource
import com.openbank.marvel.domain.MarvelCharacter
import com.openbank.marvel.domain.MarvelCharacterDetail
import com.openbank.marvel.domain.MarvelError
import com.openbank.marvel.framework.extension.result
import com.openbank.marvel.framework.extension.toError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class MarvelFramework(
    private val service: MarvelService
) : CharacterRemoteDataSource, CharacterDetailRemoteDataSource {
    override fun getCharacters(): Flow<Either<MarvelError, List<MarvelCharacter>>> = flow {
        emit(service.getCharacters().result().map { characters ->
            characters.map { character ->
                MarvelCharacter(
                    id = character.id,
                    name = character.name,
                    thumbnailPath = "${character.thumbnail.path}.${character.thumbnail.extension}",
                )
            }
        })
    }.catch { e ->
        emit(e.toError().left())
    }

    override fun getCharacterDetail(id: Int): Flow<Either<MarvelError, MarvelCharacterDetail>> =
        flow {
            emit(service.getCharacterDetail(id = id).result().map { characters ->
                characters.map { character ->
                    MarvelCharacterDetail(
                        id = character.id,
                        name = character.name,
                        description = character.description,
                        modified = character.modified,
                        thumbnailPath = "${character.thumbnail.path}.${character.thumbnail.extension}",
                        comics = character.comics.items.map { it.name },
                        series = character.series.items.map { it.name },
                    )
                }.first()
            })
        }.catch { e ->
            emit(e.toError().left())
        }
}