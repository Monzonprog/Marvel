package com.openbank.marvel.presentation.di

import com.openbank.marvel.presentation.navigator.MarvelNavigator
import com.openbank.marvel.presentation.navigator.MarvelNavigatorLifeCycle
import com.openbank.marvel.presentation.screen.detail.DetailViewModel
import com.openbank.marvel.presentation.screen.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { MarvelNavigator(lifeCycle = MarvelNavigatorLifeCycle(app = androidApplication())) }

    viewModel { HomeViewModel(getCharacters = get(), navigator = get()) }
    viewModel { (id: Int) ->
        DetailViewModel(id = id, getCharacterDetail = get(), navigator = get())
    }
}