package com.geekstudio.recipeplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekstudio.recipeplanner.data.local.entity.FavoriteRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query(
        """
        SELECT *
        FROM favorite_recipes
        """
    )
    fun observeFavorites(): Flow<List<FavoriteRecipeEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun addFavorite(
        favorite: FavoriteRecipeEntity
    )

    @Query(
        """
        DELETE FROM favorite_recipes
        WHERE recipeId = :recipeId
        """
    )
    suspend fun removeFavorite(
        recipeId: String
    )

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM favorite_recipes
            WHERE recipeId = :recipeId
        )
        """
    )
    suspend fun isFavorite(
        recipeId: String
    ): Boolean
}