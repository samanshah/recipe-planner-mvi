package com.geekstudio.recipeplanner.fake

import android.R.attr.category
import com.geekstudio.recipeplanner.domain.model.Recipe
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRecipeRepository : RecipeRepository {

    private val recipesFlow =
        MutableStateFlow(
            emptyList<Recipe>()
        )

    override fun observeRecipes(): Flow<List<Recipe>> {
        return recipesFlow
    }

    override suspend fun refreshRecipes(
        query: String
    ) {

        recipesFlow.value =
            listOf(
                Recipe(
                    id = "1",
                    title = query,
                    imageUrl = "",
                    category = "",
                    instructions = "",
                    isFavorite = false
                )
            )
    }

    override suspend fun getRecipeById(
        recipeId: String
    ): Recipe? {

        return recipesFlow.value.firstOrNull {
            it.id == recipeId
        }
    }

    override fun observeFavorites(): Flow<List<Recipe>> {
        return MutableStateFlow(emptyList())
    }

    override suspend fun addFavorite(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavorite(recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun isFavorite(recipeId: String): Boolean {
        TODO("Not yet implemented")
    }
}