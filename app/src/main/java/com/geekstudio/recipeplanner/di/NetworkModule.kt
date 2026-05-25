package com.geekstudio.recipeplanner.di

import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import kotlin.jvm.java
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(
                "https://www.themealdb.com/api/json/v1/1/"
            )
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json"
                        .toMediaType()
                )
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideRecipeApi(
        retrofit: Retrofit
    ): RecipeApi {

        return retrofit.create(
            RecipeApi::class.java
        )

    }

}