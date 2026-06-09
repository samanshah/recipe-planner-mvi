package com.geekstudio.recipeplanner.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun observeFavorites(): Flow<List<String>>

    suspend fun addFavorite(
        recipeId: String
    )

    suspend fun removeFavorite(
        recipeId: String
    )

    suspend fun isFavorite(
        recipeId: String
    ): Boolean

}