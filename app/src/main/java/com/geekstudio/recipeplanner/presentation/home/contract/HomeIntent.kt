package com.geekstudio.recipeplanner.presentation.home.contract

sealed interface HomeIntent {

    data class SearchRecipes(
        val query: String
    ) : HomeIntent

    data object Refresh : HomeIntent

    data class RecipeClicked(
        val recipeId: String
    ) : HomeIntent

    data class ToggleFavorite(
        val recipeId: String
    ) : HomeIntent

}