package com.openbank.marvel.presentation.extension

import com.openbank.marvel.R
import com.openbank.marvel.domain.MarvelError

fun MarvelError.toStringRes(): Int {
    return when (this) {
        is MarvelError.UnknownHost -> R.string.error_unknown_host
        is MarvelError.Unknown -> R.string.error_unknown
    }
}