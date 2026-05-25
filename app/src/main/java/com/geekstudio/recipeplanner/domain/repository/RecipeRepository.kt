package com.geekstudio.recipeplanner.domain.repository

import com.geekstudio.recipeplanner.domain.model.Recipe

interface RecipeRepository {

    suspend fun searchRecipes(
        query: String
    ): List<Recipe>

}