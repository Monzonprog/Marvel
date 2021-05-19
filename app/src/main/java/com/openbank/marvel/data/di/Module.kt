package com.openbank.marvel.data.di

import com.openbank.marvel.data.character.CharacterRepository
import org.koin.dsl.module


val dataModule = module {
    single { CharacterRepository(remote = get()) }
}