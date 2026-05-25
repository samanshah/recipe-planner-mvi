package com.geekstudio.recipeplanner.presentation.home.contract

sealed interface HomeEffect {

    data class NavigateToDetail(
        val recipeId: String
    ) : HomeEffect

    data class ShowSnackbar(
        val message: String
    ) : HomeEffect

}