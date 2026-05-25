package com.geekstudio.recipeplanner.data.local.database

import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}