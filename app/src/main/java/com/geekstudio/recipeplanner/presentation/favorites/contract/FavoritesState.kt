package com.geekstudio.recipeplanner.presentation.favorites.contract

import com.geekstudio.recipeplanner.domain.model.Recipe

data class FavoritesState(

    val isLoading: Boolean = false,

    val recipes: List<Recipe> = emptyList(),

    val error: String? = null

)