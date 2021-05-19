package com.openbank.marvel.framework.di

import com.openbank.marvel.data.character.CharacterRemoteDataSource
import com.openbank.marvel.data.character_detail.CharacterDetailRemoteDataSource
import com.openbank.marvel.framework.marvel.MarvelAPI
import com.openbank.marvel.framework.marvel.MarvelFramework
import com.openbank.marvel.framework.marvel.MarvelInterceptor
import com.openbank.marvel.framework.marvel.MarvelService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val frameworkModule = module {
    factory<CharacterRemoteDataSource> { get<MarvelFramework>() }
    factory<CharacterDetailRemoteDataSource> { get<MarvelFramework>() }

    single { MarvelFramework(service = get()) }
    single {
        Retrofit.Builder()
            .baseUrl(MarvelAPI.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .addInterceptor(MarvelInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }
}