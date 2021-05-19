package com.openbank.marvel.framework.marvel

object MarvelAPI {
    const val BASE_URL = "https://gateway.marvel.com/"

    const val ID_PATH = "id"

    const val CHARACTERS_METHOD = "v1/public/characters"
    const val CHARACTER_METHOD = "v1/public/characters/{$ID_PATH}"


    const val API_KEY_QUERY_PARAM = "apikey"
    const val HASH_QUERY_PARAM = "hash"
    const val TS_QUERY_PARAM = "ts"

    const val PUBLIC_KEY = "700cef9d8fa3892dae02228dda36aa54"
    const val PRIVATE_KEY = "6d2cc7b9266facddfb20ac8e87078425f372828c"
}