package com.geekstudio.recipeplanner.data.remote.mapper

import android.R.attr.category
import com.geekstudio.recipeplanner.data.remote.dto.RecipeDto
import com.geekstudio.recipeplanner.domain.model.Recipe

fun RecipeDto.toDomain(): Recipe {

    return Recipe(
        id = id,
        title = title,
        imageUrl = imageUrl,
        category = "",
        instructions = "",
        isFavorite = false
    )

}