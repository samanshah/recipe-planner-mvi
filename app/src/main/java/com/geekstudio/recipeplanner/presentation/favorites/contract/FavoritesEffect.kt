package com.geekstudio.recipeplanner.presentation.favorites.contract

sealed interface FavoritesEffect {

    data class NavigateToDetail(
        val recipeId: String
    ) : FavoritesEffect

}