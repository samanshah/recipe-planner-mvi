package com.geekstudio.recipeplanner.domain.repository

import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import com.geekstudio.recipeplanner.data.remote.mapper.toDomain
import com.geekstudio.recipeplanner.domain.model.Recipe
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi
) : RecipeRepository {

    override suspend fun searchRecipes(
        query: String
    ): List<Recipe> {

        return api.searchRecipes(query)
            .meals
            ?.map { it.toDomain() }
            ?: emptyList()

    }

}