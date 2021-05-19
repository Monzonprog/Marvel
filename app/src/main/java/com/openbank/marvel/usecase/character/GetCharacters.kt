package com.openbank.marvel.usecase.character

import com.openbank.marvel.data.character.CharacterRepository

class GetCharacters(private val repository: CharacterRepository) {
    operator fun invoke() = repository.getCharacters()
}