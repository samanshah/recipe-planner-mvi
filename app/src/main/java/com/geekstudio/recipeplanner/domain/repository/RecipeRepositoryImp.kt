package com.geekstudio.recipeplanner.domain.repository

import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.mapper.toDomain
import com.geekstudio.recipeplanner.data.local.mapper.toEntity
import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import com.geekstudio.recipeplanner.data.remote.mapper.toDomain
import com.geekstudio.recipeplanner.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi,
    private val dao: RecipeDao
) : RecipeRepository {

    override fun observeRecipes(
        query: String
    ): Flow<List<Recipe>> {

        return dao.observeRecipes(query)
            .map { entities ->

                entities.map {
                    it.toDomain()
                }

            }

    }

    override suspend fun refreshRecipes(
        query: String
    ) {

        val recipes =
            api.searchRecipes(query)
                .meals
                ?.map { dto ->

                    dto.toDomain()

                }
                ?: emptyList()

        dao.insertRecipes(
            recipes.map {
                it.toEntity()
            }
        )

    }

    override suspend fun toggleFavorite(
        recipeId: String
    ) {

        dao.toggleFavorite(recipeId)

    }

}