package com.openbank.marvel.usecase.character_detail

import com.openbank.marvel.data.character_detail.CharacterDetailRepository

class GetCharacterDetail(private val repository: CharacterDetailRepository) {
    operator fun invoke(id: Int) = repository.getCharacterDetail(id = id)
}