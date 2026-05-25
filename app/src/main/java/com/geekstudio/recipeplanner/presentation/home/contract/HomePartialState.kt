package com.geekstudio.recipeplanner.presentation.home.contract

import com.geekstudio.recipeplanner.domain.model.Recipe

sealed interface HomePartialState {

    data object Loading : HomePartialState

    data class RecipesLoaded(
        val recipes: List<Recipe>
    ) : HomePartialState

    data class Error(
        val message: String
    ) : HomePartialState

    data class QueryChanged(
        val query: String
    ) : HomePartialState

}