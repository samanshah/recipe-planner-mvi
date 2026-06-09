package com.geekstudio.recipeplanner.data.repository

import com.geekstudio.recipeplanner.data.local.dao.FavoriteDao
import com.geekstudio.recipeplanner.data.local.entity.FavoriteRecipeEntity
import com.geekstudio.recipeplanner.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override fun observeFavorites(): Flow<List<String>> {

        return favoriteDao
            .observeFavorites()
            .map { favorites ->

                favorites.map {
                    it.recipeId
                }

            }

    }

    override suspend fun addFavorite(
        recipeId: String
    ) {

        favoriteDao.addFavorite(
            FavoriteRecipeEntity(
                recipeId = recipeId
            )
        )

    }

    override suspend fun removeFavorite(
        recipeId: String
    ) {

        favoriteDao.removeFavorite(
            recipeId
        )

    }

    override suspend fun isFavorite(
        recipeId: String
    ): Boolean {

        return favoriteDao.isFavorite(
            recipeId
        )

    }

}