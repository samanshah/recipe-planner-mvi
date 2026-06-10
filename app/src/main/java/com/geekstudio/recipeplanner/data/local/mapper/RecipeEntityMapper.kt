package com.geekstudio.recipeplanner.data.local.mapper

import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity
import com.geekstudio.recipeplanner.domain.model.Recipe

fun RecipeEntity.toDomain(
    isFavorite: Boolean = false
): Recipe {

    return Recipe(
        id = id,
        title = title,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        category = category,
        instructions = instructions
    )

}

fun Recipe.toEntity(): RecipeEntity {

    return RecipeEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions
    )

}