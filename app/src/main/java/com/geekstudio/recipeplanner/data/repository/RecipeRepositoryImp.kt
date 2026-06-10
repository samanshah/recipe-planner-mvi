package com.geekstudio.recipeplanner.data.repository

import com.geekstudio.recipeplanner.data.local.dao.FavoriteDao
import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.dao.SearchHistoryDao
import com.geekstudio.recipeplanner.data.local.entity.SearchHistoryEntity
import com.geekstudio.recipeplanner.data.local.mapper.toDomain
import com.geekstudio.recipeplanner.data.local.mapper.toEntity
import com.geekstudio.recipeplanner.data.local.mapper.toFavoriteEntity
import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import com.geekstudio.recipeplanner.data.remote.mapper.toDomain
import com.geekstudio.recipeplanner.domain.model.Recipe
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi,
    private val recipeDao: RecipeDao,
    private val searchHistoryDao: SearchHistoryDao,
    private val favoriteDao: FavoriteDao
) : RecipeRepository {

    override fun observeRecipes(): Flow<List<Recipe>> {
        return combine(
            recipeDao.observeRecipes(), favoriteDao.observeFavorites()
        ) { recipes, favorites ->

            val favoriteIds = favorites.map { it.id }.toSet()

            recipes.map {

                it.toDomain(
                    isFavorite = it.id in favoriteIds
                )

            }

        }
    }

    override suspend fun refreshRecipes(
        query: String
    ) {

        val favoriteIds = favoriteDao.getFavoriteIds()

        val recipes = api.searchRecipes(query).meals?.map { dto ->

            dto.toDomain().copy(
                isFavorite = dto.id in favoriteIds
            )

        } ?: emptyList()

        recipeDao.clearRecipes()

        recipeDao.insertRecipes(
            recipes.map {
                it.toEntity()
            })

        if (query.isNotBlank()) {

            searchHistoryDao.insertSearchQuery(
                SearchHistoryEntity(
                    queryStr = query.trim(),
                    timestamp = System.currentTimeMillis()
                )
            )

        }

    }

    override suspend fun getRecipeById(
        recipeId: String
    ): Recipe? {

        val recipe =
            recipeDao.getRecipeById(recipeId)?.toDomain() ?: favoriteDao.getRecipeById(recipeId)
                ?.toDomain() ?: return null

        return recipe.copy(
            isFavorite = favoriteDao.isFavorite(recipeId)
        )

    }

    override fun observeFavorites(): Flow<List<Recipe>> {

        return favoriteDao.observeFavorites().map { entities ->

                entities.map {
                    it.toDomain()
                }

            }

    }

    override suspend fun addFavorite(
        recipe: Recipe
    ) {

        favoriteDao.addFavorite(
            recipe.toFavoriteEntity()
        )

    }

    override suspend fun removeFavorite(
        recipeId: String
    ) {

        favoriteDao.removeFavorite(recipeId)

    }

    override suspend fun isFavorite(
        recipeId: String
    ): Boolean {

        return favoriteDao.isFavorite(
            recipeId
        )

    }

}