package com.geekstudio.recipeplanner.di

import kotlin.jvm.java
import android.content.Context
import androidx.room.Room
import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.dao.SearchHistoryDao
import com.geekstudio.recipeplanner.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "recipe_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideRecipeDao(
        database: AppDatabase
    ): RecipeDao {

        return database.recipeDao()

    }

    @Provides
    @Singleton
    fun provideSearchHistoryDao(
        database: AppDatabase
    ): SearchHistoryDao {

        return database.searchHistoryDao()

    }

}