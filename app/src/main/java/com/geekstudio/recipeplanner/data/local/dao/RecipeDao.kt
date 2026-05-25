package com.geekstudio.recipeplanner.data.local.dao

import androidx.room.*
import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("""
        SELECT * FROM recipes
        WHERE title LIKE '%' || :query || '%'
    """)
    fun observeRecipes(
        query: String
    ): Flow<List<RecipeEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRecipes(
        recipes: List<RecipeEntity>
    )

    @Query("""
        UPDATE recipes
        SET isFavorite = NOT isFavorite
        WHERE id = :recipeId
    """)
    suspend fun toggleFavorite(
        recipeId: String
    )

    @Query("""
        SELECT * FROM recipes
        WHERE isFavorite = 1
    """)
    fun observeFavorites():
            Flow<List<RecipeEntity>>

}