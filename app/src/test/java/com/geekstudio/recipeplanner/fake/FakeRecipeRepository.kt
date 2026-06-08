package com.geekstudio.recipeplanner.fake


import com.geekstudio.recipeplanner.domain.model.Recipe
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRecipeRepository :
    RecipeRepository {

    override fun observeRecipes(
        query: String
    ): Flow<List<Recipe>> {

        return flowOf(
            listOf(
                Recipe(
                    id = "1",
                    title = "Pizza",
                    imageUrl = ""
                )
            )
        )

    }

    override suspend fun refreshRecipes(
        query: String
    ) {

    }

    override suspend fun toggleFavorite(
        recipeId: String
    ) {

    }

}