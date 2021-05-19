package com.openbank.marvel.presentation.result

sealed class MarvelResult <out T> {
    class Loading<out T> : MarvelResult<T>()
    data class Success<out T>(val data: T) : MarvelResult<T>()
}