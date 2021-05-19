package com.openbank.marvel.presentation.screen.detail

import androidx.lifecycle.ViewModel
import com.openbank.marvel.R
import com.openbank.marvel.domain.MarvelCharacterDetail
import com.openbank.marvel.presentation.extension.toStringRes
import com.openbank.marvel.presentation.navigator.MarvelNavigator
import com.openbank.marvel.presentation.result.MarvelResult
import com.openbank.marvel.usecase.character_detail.GetCharacterDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.*

class DetailViewModel(
    private val id: Int,
    private val getCharacterDetail: GetCharacterDetail,
    private val navigator: MarvelNavigator,
) : ViewModel() {
    val character: Flow<MarvelResult<MarvelCharacterDetail>> = flow {
        emit(MarvelResult.Loading())
        getCharacterDetail(id = id).collect { result ->
            result.fold(
                { error ->
                    navigator.showToast(error.toStringRes())
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