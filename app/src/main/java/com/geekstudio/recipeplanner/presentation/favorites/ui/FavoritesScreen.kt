package com.geekstudio.recipeplanner.presentation.favorites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geekstudio.recipeplanner.core.ui.components.EmptyState
import com.geekstudio.recipeplanner.core.ui.spacing.AppSpacing
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesEffect
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesIntent
import com.geekstudio.recipeplanner.presentation.favorites.viewmodel.FavoritesViewModel
import com.geekstudio.recipeplanner.presentation.home.component.RecipeCard

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(), onNavigateToDetail: (String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                is FavoritesEffect.NavigateToDetail -> {
                    onNavigateToDetail(
                        effect.recipeId
                    )
                }

                is FavoritesEffect.ShowUndoSnackbar -> {

                    val result = snackbarHostState.showSnackbar(
                        message = "Removed from favorites", actionLabel = "Undo"
                    )

                    if (result == SnackbarResult.ActionPerformed) {

                        viewModel.undoRemoveFavorite()

                    }

                }

            }

        }

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }) { paddingValues ->
        paddingValues

        if (state.recipes.isEmpty()) {

            EmptyState(
                message = "No favorite recipes yet"
            )

            return@Scaffold
        }

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(
                bottom = 96.dp,
                top = AppSpacing.Small,
                start = AppSpacing.Medium,
                end = AppSpacing.Medium
            ),
            verticalArrangement = Arrangement.spacedBy(
                AppSpacing.Medium
            )
        ) {
            items(
                items = state.recipes,
                key = { it.id }
            ) { recipe ->

                RecipeCard(
                    modifier = Modifier.animateItem(),
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

}