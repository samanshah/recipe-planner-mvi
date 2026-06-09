package com.geekstudio.recipeplanner.presentation.detail.contract

import com.geekstudio.recipeplanner.domain.model.Recipe

data class DetailState(

    val isLoading: Boolean = true,

    val recipe: Recipe? = null,

    val error: String? = null

)