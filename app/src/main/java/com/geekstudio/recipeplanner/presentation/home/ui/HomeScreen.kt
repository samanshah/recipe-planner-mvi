package com.geekstudio.recipeplanner.presentation.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import com.geekstudio.recipeplanner.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state
        .collectAsStateWithLifecycle()

    Column {

        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {

                viewModel.onIntent(
                    HomeIntent.SearchRecipes(it)
                )

            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Recipes count: ${state.recipes.size}"
        )

    }

}