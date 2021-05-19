package com.openbank.marvel.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.openbank.marvel.R
import com.openbank.marvel.domain.MarvelCharacter
import com.openbank.marvel.presentation.extension.toStringRes
import com.openbank.marvel.presentation.navigator.MarvelNavigator
import com.openbank.marvel.presentation.result.MarvelResult
import com.openbank.marvel.usecase.character.GetCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val getCharacters: GetCharacters,
    private val navigator: MarvelNavigator,
) : ViewModel() {
    val characters: Flow<MarvelResult<List<MarvelCharacter>>> = flow {
        emit(MarvelResult.Loading())
        getCharacters().collect { result ->
            result.fold(
                { error ->
                    navigator.showToast(error.toStringRes())
                    emit(MarvelResult.Success(data = emptyList<MarvelCharacter>()))
                },
                { characters ->
                    emit(MarvelResult.Success(data = characters))
                }
            )
        }
    }.catch {
        navigator.showToast(R.string.error_unknown)
        emit(MarvelResult.Success(data = emptyList<MarvelCharacter>()))
    }.flowOn(Dispatchers.Main)

    fun characterClicked(id: Int) {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id = id))
    }
}