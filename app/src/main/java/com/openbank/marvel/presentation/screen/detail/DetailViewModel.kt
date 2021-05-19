package com.openbank.marvel.presentation.screen.detail

import androidx.lifecycle.ViewModel
import com.openbank.marvel.R
import com.openbank.marvel.domain.MarvelCharacter
import com.openbank.marvel.presentation.extension.toStringRes
import com.openbank.marvel.presentation.navigator.MarvelNavigator
import com.openbank.marvel.presentation.result.MarvelResult
import com.openbank.marvel.usecase.character.GetCharacterDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DetailViewModel(
    private val id: Int,
    private val getCharacterDetail: GetCharacterDetail,
    private val navigator: MarvelNavigator,
) : ViewModel() {
    val character: Flow<MarvelResult<MarvelCharacter>> = flow {
        emit(MarvelResult.Loading())
        getCharacterDetail(id = id).collect { result ->
            result.fold(
                { error ->
                    navigator.showToast(error.toStringRes())
                    emit(MarvelResult.Success(data = MarvelCharacter()))
                },
                { character ->
                    emit(MarvelResult.Success(data = character))
                }
            )
        }
    }.catch {
        navigator.showToast(R.string.error_unknown)
    }.flowOn(Dispatchers.Main)
}