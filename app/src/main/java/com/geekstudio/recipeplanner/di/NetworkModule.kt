package com.geekstudio.recipeplanner.di

import android.util.Log
import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import kotlin.jvm.java
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(object : Interceptor {
//
//                override fun intercept(chain: Interceptor.Chain): Response {
//
//                    val request = chain.request()
//
//                    Log.d("API", "URL: ${request.url}")
//                    Log.d("API", "METHOD: ${request.method}")
//
//                    val response = chain.proceed(request)
//
//                    Log.d("API", "CODE: ${response.code}")
//
//                    return response
//                }
//            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(
                "https://www.themealdb.com/api/json/v1/1/"
            )
            .client(okHttpClient)
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