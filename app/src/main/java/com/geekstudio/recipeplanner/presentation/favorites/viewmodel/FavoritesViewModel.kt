package com.geekstudio.recipeplanner.presentation.favorites.viewmodel

import androidx.lifecycle.ViewModel
import com.geekstudio.recipeplanner.domain.repository.FavoriteRepository
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(

    private val favoriteRepository: FavoriteRepository

) : ViewModel() {

    private val _state = MutableStateFlow(
        FavoritesState()
    )

    val state = _state.asStateFlow()

}