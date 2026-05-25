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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>()

    val effect = _effect.receiveAsFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        observeSearch()
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

            }

        }

    }

    private fun observeSearch() {

        viewModelScope.launch {

            searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->

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

            reduce(
                HomePartialState.Loading
            )

            try {

                val recipes =
                    repository.searchRecipes(query)

                reduce(
                    HomePartialState.RecipesLoaded(
                        recipes
                    )
                )

            } catch (e: Exception) {

                reduce(
                    HomePartialState.Error(
                        e.message
                            ?: "Unknown error"
                    )
                )

            }

        }

    }

    private fun reduce(
        partialState: HomePartialState
    ) {

        _state.update { currentState ->

            HomeReducer.reduce(
                currentState,
                partialState
            )

        }

    }

}