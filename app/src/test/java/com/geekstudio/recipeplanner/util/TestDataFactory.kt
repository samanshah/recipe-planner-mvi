package com.geekstudio.recipeplanner.util

import com.geekstudio.recipeplanner.domain.model.Recipe

object TestDataFactory {

    fun recipe(
        id: String = "1",
        title: String = "Pizza"
    ) = Recipe(
        id = id,
        title = title,
        imageUrl = "",
        category = "",
        instructions = "",
        isFavorite = false
    )

}