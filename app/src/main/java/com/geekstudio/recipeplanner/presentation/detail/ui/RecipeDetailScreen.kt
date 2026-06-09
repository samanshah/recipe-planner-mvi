package com.geekstudio.recipeplanner.presentation.detail.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.geekstudio.recipeplanner.core.ui.components.EmptyState
import com.geekstudio.recipeplanner.presentation.detail.contract.DetailIntent
import com.geekstudio.recipeplanner.presentation.detail.viewmodel.DetailViewModel

@Composable
fun RecipeDetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when {

        state.isLoading -> {

            CircularProgressIndicator()

        }

        state.recipe == null -> {

            EmptyState(
                message = "Recipe not found"
            )

        }

        else -> {

            val recipe = state.recipe!!

            IconButton(
                onClick = {

                    viewModel.onIntent(
                        DetailIntent.ToggleFavorite
                    )

                }
            ) {

                Icon(
                    imageVector =
                        if (recipe.isFavorite)
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                    contentDescription = null
                )

            }

            LazyColumn {

                item {

                    AsyncImage(
                        model = recipe.imageUrl, contentDescription = null
                    )

                }

                item {

                    Text(
                        text = recipe.title
                    )

                }

                item {

                    Text(
                        text = recipe.category
                    )

                }

                item {

                    Text(
                        text = recipe.instructions
                    )

                }

            }

        }

    }

}