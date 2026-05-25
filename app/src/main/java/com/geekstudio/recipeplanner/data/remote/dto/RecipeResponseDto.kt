package com.geekstudio.recipeplanner.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseDto(
    val meals: List<RecipeDto>?
)