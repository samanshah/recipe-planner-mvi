package com.geekstudio.recipeplanner.presentation.home.reducer

import com.geekstudio.recipeplanner.presentation.home.contract.HomeState

object HomeReducer {

    fun reduce(
        currentState: HomeState,
        newState: HomeState
    ): HomeState {

        return currentState.copy(

            isLoading = newState.isLoading,

            recipes = newState.recipes,

            searchQuery = newState.searchQuery,

            error = newState.error

        )

    }

}