package com.geekstudio.recipeplanner.domain.model

data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val category: String,
    val instructions: String,
)