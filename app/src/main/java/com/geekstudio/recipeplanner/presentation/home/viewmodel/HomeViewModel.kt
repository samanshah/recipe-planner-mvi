package com.geekstudio.recipeplanner.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.geekstudio.recipeplanner.presentation.home.contract.HomeEffect
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import com.geekstudio.recipeplanner.presentation.home.contract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state =
        MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    private val _effect =
        Channel<HomeEffect>()

    val effect =
        _effect.receiveAsFlow()

    fun onIntent(
        intent: HomeIntent
    ) {

        when (intent) {

            is HomeIntent.SearchRecipes -> {

                _state.update {

                    it.copy(
                        searchQuery = intent.query
                    )

                }

            }

            is HomeIntent.Refresh -> {

            }

            is HomeIntent.RecipeClicked -> {

            }

            is HomeIntent.ToggleFavorite -> {

            }

        }

    }

}