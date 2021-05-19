package com.openbank.marvel

import android.app.Application
import com.openbank.marvel.data.di.dataModule
import com.openbank.marvel.framework.di.frameworkModule
import com.openbank.marvel.presentation.di.presentationModule
import com.openbank.marvel.presentation.navigator.MarvelNavigator
import com.openbank.marvel.usecase.di.useCaseModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelApp : Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupNavigator()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelApp)
            modules(presentationModule, useCaseModule, dataModule, frameworkModule)
        }
    }

    private fun setupNavigator() {
        get<MarvelNavigator>()
    }
}