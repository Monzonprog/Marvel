package com.openbank.marvel.usecase.character

import arrow.core.left
import arrow.core.right
import com.openbank.marvel.data.character.CharacterRemoteDataSource
import com.openbank.marvel.data.character.CharacterRepository
import com.openbank.marvel.domain.MarvelCharacter
import com.openbank.marvel.domain.MarvelError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersIT {
    @Mock
    private lateinit var remote: CharacterRemoteDataSource
    private lateinit var repository: CharacterRepository
    private lateinit var getCharacters: GetCharacters

    @Before
    fun setup() {
        repository = CharacterRepository(dispatcher = Dispatchers.Unconfined, remote = remote)
        getCharacters = GetCharacters(dispatcher = Dispatchers.Unconfined, repository = repository)
    }

    @Test
    fun `given remote returns unknown error, when getCharacters invokes operator, getCharacters returns unknown error`() =
        runBlocking {
            Mockito.`when`(remote.getCharacters())
                .thenReturn(flow { emit(MarvelError.Unknown.left()) })

            val result = getCharacters().first()

            Assert.assertEquals(MarvelError.Unknown.left(), result)
        }

    @Test
    fun `given remote returns a list of characters, when getCharacters invokes operator, getCharacters returns the same list`() =
        runBlocking {
            Mockito.`when`(remote.getCharacters())
                .thenReturn(flow { emit(emptyList<MarvelCharacter>().right()) })

            val result = getCharacters().first()

            Assert.assertEquals(emptyList<MarvelCharacter>().right(), result)
        }
}