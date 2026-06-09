package com.geekstudio.recipeplanner.di

import com.geekstudio.recipeplanner.data.repository.FavoriteRepositoryImpl
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import com.geekstudio.recipeplanner.data.repository.RecipeRepositoryImpl
import com.geekstudio.recipeplanner.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    abstract fun bindFavoriteRepository(
        repository: FavoriteRepositoryImpl
    ): FavoriteRepository

}