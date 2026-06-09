package com.geekstudio.recipeplanner.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.dao.SearchHistoryDao
import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity
import com.geekstudio.recipeplanner.data.local.entity.SearchHistoryEntity

@Database(
    entities = [
        RecipeEntity::class,
        SearchHistoryEntity::class,
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    abstract fun searchHistoryDao(): SearchHistoryDao

}