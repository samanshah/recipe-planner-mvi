package com.geekstudio.recipeplanner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val imageUrl: String,

    val isFavorite: Boolean = false

)