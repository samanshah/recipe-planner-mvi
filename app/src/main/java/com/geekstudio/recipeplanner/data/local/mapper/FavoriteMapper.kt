package com.geekstudio.recipeplanner.data.local.mapper

import com.geekstudio.recipeplanner.data.local.entity.FavoriteRecipeEntity
import com.geekstudio.recipeplanner.domain.model.Recipe

fun Recipe.toFavoriteEntity() =
    FavoriteRecipeEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        isFavorite = true
    )

fun FavoriteRecipeEntity.toDomain() =
    Recipe(
        id = id,
        title = title,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        isFavorite = true
    )