package com.geekstudio.recipeplanner.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(

    @SerialName("idMeal")
    val id: String,

    @SerialName("strMeal")
    val title: String,

    @SerialName("strMealThumb")
    val imageUrl: String,

)