package com.openbank.marvel.framework.extension

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.openbank.marvel.domain.MarvelError
import com.openbank.marvel.framework.marvel.MarvelCharacter
import com.openbank.marvel.framework.marvel.MarvelResponse
import retrofit2.Response

fun Response<MarvelResponse>.result(): Either<MarvelError, List<MarvelCharacter>> =
    if (isSuccessful) {
        body()?.data?.results?.right() ?: MarvelError.Unknown.left()
    } else {
        MarvelError.Unknown.left()
    }
