package com.geekstudio.recipeplanner.data.remote.api

import com.geekstudio.recipeplanner.data.remote.dto.RecipeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("search.php")
    suspend fun searchRecipes(
        @Query("s")
        query: String
    ): RecipeResponseDto

}