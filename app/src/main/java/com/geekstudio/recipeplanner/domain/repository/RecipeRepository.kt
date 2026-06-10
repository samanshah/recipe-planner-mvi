package com.geekstudio.recipeplanner.domain.repository

import com.geekstudio.recipeplanner.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun observeRecipes(): Flow<List<Recipe>>

    suspend fun refreshRecipes(
        query: String
    )

    suspend fun getRecipeById(
        recipeId: String
    ): Recipe?

    fun observeFavorites(): Flow<List<Recipe>>

    suspend fun addFavorite(
        recipe: Recipe
    )

    suspend fun removeFavorite(
        recipeId: String
    )

    suspend fun isFavorite(
        recipeId: String
    ): Boolean

}