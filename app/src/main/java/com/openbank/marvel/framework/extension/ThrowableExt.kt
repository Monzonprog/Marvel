package com.openbank.marvel.framework.extension

import com.openbank.marvel.domain.MarvelError
import java.net.UnknownHostException

fun Throwable.toError(): MarvelError {
    return when (this) {
        is UnknownHostException -> MarvelError.UnknownHost
        else -> MarvelError.Unknown
    }
}