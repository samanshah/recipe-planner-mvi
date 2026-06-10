package com.geekstudio.recipeplanner.presentation.favorites.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesEffect
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesIntent
import com.geekstudio.recipeplanner.presentation.favorites.viewmodel.FavoritesViewModel
import com.geekstudio.recipeplanner.presentation.home.component.RecipeCard

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {

        items(
            state.recipes
        ) { recipe ->

            RecipeCard(
                recipe = recipe,

                onClick = {

                    onNavigateToDetail(recipe.id)

                    viewModel.onIntent(
                        FavoritesIntent.RecipeClicked(
                            recipe.id
                        )
                    )

                },

                onFavoriteClick = {

                    viewModel.onIntent(
                        FavoritesIntent.RemoveFavorite(
                            recipe.id
                        )
                    )

                }

            )

        }

    }

}