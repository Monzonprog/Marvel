package com.openbank.marvel.domain

sealed class MarvelError {
    object UnknownHost : MarvelError()
    object Unknown : MarvelError()
}