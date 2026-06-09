package com.geekstudio.recipeplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query(
        """
        SELECT *
        FROM recipes
        ORDER BY title ASC
        """
    )
    fun observeRecipes(): Flow<List<RecipeEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRecipes(
        recipes: List<RecipeEntity>
    )

    @Query(
        """
        DELETE FROM recipes
        """
    )
    suspend fun clearRecipes()

    @Query(
        """
    SELECT *
    FROM recipes
    WHERE isFavorite = 1
    ORDER BY title
    """
    )
    fun observeFavorites(): Flow<List<RecipeEntity>>

    @Query(
        """
    SELECT *
    FROM recipes
    WHERE id = :recipeId
    LIMIT 1
    """
    )
    suspend fun getRecipeById(
        recipeId: String
    ): RecipeEntity?

}