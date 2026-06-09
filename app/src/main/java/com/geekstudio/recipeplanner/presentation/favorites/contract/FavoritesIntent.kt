package com.geekstudio.recipeplanner.presentation.favorites.contract

sealed interface FavoritesIntent {

    data object LoadFavorites : FavoritesIntent

    data class RecipeClicked(
        val recipeId: String
    ) : FavoritesIntent

    data class RemoveFavorite(
        val recipeId: String
    ) : FavoritesIntent

}