package com.openbank.marvel.framework.marvel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET(MarvelAPI.CHARACTERS_METHOD)
    suspend fun getCharacters(): Response<MarvelResponse>

    @GET(MarvelAPI.CHARACTER_METHOD)
    suspend fun getCharacterDetail(@Path(MarvelAPI.ID_PATH) id: Int): Response<MarvelResponse>
}