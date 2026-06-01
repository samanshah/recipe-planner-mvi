package com.geekstudio.recipeplanner.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import com.geekstudio.recipeplanner.presentation.home.contract.HomeEffect
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import com.geekstudio.recipeplanner.presentation.home.contract.HomePartialState
import com.geekstudio.recipeplanner.presentation.home.contract.HomeState
import com.geekstudio.recipeplanner.presentation.home.reducer.HomeReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)

    val effect = _effect.receiveAsFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        observeSearch()
        observeRecipes("")
    }

    fun onIntent(
        intent: HomeIntent
    ) {

        when (intent) {

            is HomeIntent.SearchRecipes -> {

                searchQuery.value = intent.query

            }

            is HomeIntent.Refresh -> {

                searchRecipes(
                    _state.value.searchQuery
                )

            }

            is HomeIntent.RecipeClicked -> {

                viewModelScope.launch {

                    _effect.send(
                        HomeEffect.NavigateToDetail(
                            intent.recipeId
                        )
                    )

                }

            }

            is HomeIntent.ToggleFavorite -> {
                viewModelScope.launch {

                    repository.toggleFavorite(
                        intent.recipeId
                    )

                }
            }

            is HomeIntent.Retry -> {

                searchRecipes(
                    _state.value.lastQuery
                )

            }

        }

    }

    private fun observeRecipes(
        query: String
    ) {

        viewModelScope.launch {

            repository.observeRecipes(query).collectLatest { recipes ->

                reduce(
                    HomePartialState.RecipesLoaded(
                        recipes
                    )
                )

            }

        }

    }

    private fun observeSearch() {

        viewModelScope.launch {

            searchQuery.debounce(500).distinctUntilChanged().collectLatest { query ->

                reduce(
                    HomePartialState.QueryChanged(
                        query
                    )
                )

                if (query.isNotBlank()) {

                    searchRecipes(query)

                }

            }

        }

    }

    private fun searchRecipes(
        query: String
    ) {

        viewModelScope.launch {

            reduce(HomePartialState.Loading)

            try {

                repository.refreshRecipes(query)

            } catch (e: Exception) {

                reduce(
                    HomePartialState.Error(
                        e.message ?: "Unknown error"
                    )
                )

                showError(
                    e.message ?: "Unknown error"
                )

            }

        }

    }

    private fun reduce(
        partialState: HomePartialState
    ) {

        _state.update { currentState ->

            HomeReducer.reduce(
                currentState, partialState
            )

        }

    }

    private suspend fun showError(
        message: String
    ) {

        _effect.send(
            HomeEffect.ShowSnackbar(
                message
            )
        )

    }

}