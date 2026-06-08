package com.geekstudio.recipeplanner.presentation.home.reducer

import com.geekstudio.recipeplanner.presentation.home.contract.HomePartialState
import com.geekstudio.recipeplanner.presentation.home.contract.HomeState

object HomeReducer {

    fun reduce(
        currentState: HomeState, partialState: HomePartialState
    ): HomeState {

        return when (partialState) {

            is HomePartialState.Loading -> {

                currentState.copy(
                    isLoading = true, error = null
                )

            }

            is HomePartialState.RecipesLoaded -> {

                currentState.copy(
                    isLoading = false, recipes = partialState.recipes, error = null
                )

            }

            is HomePartialState.Error -> {

                currentState.copy(
                    isLoading = false, error = partialState.message
                )

            }

            is HomePartialState.QueryChanged -> {

                currentState.copy(
                    searchQuery = partialState.query, lastQuery = partialState.query
                )

            }

            is HomePartialState.ConnectivityChanged -> {

                currentState.copy(
                    isConnected = partialState.connected
                )

            }

            is HomePartialState.SearchHintChanged -> {

                currentState.copy(
                    showSearchHint = partialState.visible
                )

            }

        }

    }

}