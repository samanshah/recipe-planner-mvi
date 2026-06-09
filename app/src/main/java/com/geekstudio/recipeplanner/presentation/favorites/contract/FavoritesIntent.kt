package com.geekstudio.recipeplanner.presentation.favorites.contract

sealed interface FavoritesIntent {

    data object LoadFavorites : FavoritesIntent

}