package com.geekstudio.recipeplanner.presentation.home.contract

import com.geekstudio.recipeplanner.domain.model.Recipe

data class HomeState(

    val isLoading: Boolean = false,

    val recipes: List<Recipe> = emptyList(),

    val searchQuery: String = "",

    val error: String? = null,

    val isOfflineMode: Boolean = false,

    val lastQuery: String = "",

    val isConnected: Boolean = true

)