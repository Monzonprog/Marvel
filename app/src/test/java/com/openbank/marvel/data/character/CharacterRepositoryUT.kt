package com.openbank.marvel.data.character

import arrow.core.left
import arrow.core.right
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
class CharacterRepositoryUT {
    @Mock
    private lateinit var remote: CharacterRemoteDataSource
    private lateinit var repository: CharacterRepository

    @Before
    fun setup() {
        repository = CharacterRepository(dispatcher = Dispatchers.Unconfined, remote = remote)
    }

    @Test
    fun `given remote returns unknown error, when repository invokes getCharacters, repository returns unknown error`() =
        runBlocking {
            Mockito.`when`(remote.getCharacters())
                .thenReturn(flow { emit(MarvelError.Unknown.left()) })

            val result = repository.getCharacters().first()

            Assert.assertEquals(MarvelError.Unknown.left(), result)
        }

    @Test
    fun `given remote returns a list of characters, when repository invokes getCharacters, repository returns the same list`() =
        runBlocking {
            Mockito.`when`(remote.getCharacters())
                .thenReturn(flow { emit(emptyList<MarvelCharacter>().right()) })

            val result = repository.getCharacters().first()

            Assert.assertEquals(emptyList<MarvelCharacter>().right(), result)
        }
}