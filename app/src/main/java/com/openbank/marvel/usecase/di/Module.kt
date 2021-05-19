package com.openbank.marvel.usecase.di

import com.openbank.marvel.usecase.character.GetCharacters
import com.openbank.marvel.usecase.character_detail.GetCharacterDetail
import org.koin.dsl.module


val useCaseModule = module {
    factory { GetCharacters(repository = get()) }
    factory { GetCharacterDetail(repository = get()) }
}