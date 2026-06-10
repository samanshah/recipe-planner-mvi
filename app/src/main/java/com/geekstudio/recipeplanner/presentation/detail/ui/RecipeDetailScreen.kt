package com.geekstudio.recipeplanner.presentation.detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.geekstudio.recipeplanner.core.ui.components.EmptyState
import com.geekstudio.recipeplanner.presentation.detail.contract.DetailIntent
import com.geekstudio.recipeplanner.presentation.detail.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when {

        state.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        state.recipe == null -> {

            EmptyState(
                message = "Recipe not found"
            )

        }

        else -> {

            val recipe = state.recipe!!

            Scaffold(

                topBar = {

                    TopAppBar(

                        title = {
                            Text("Recipe")
                        },

                        navigationIcon = {

                            IconButton(
                                onClick = onBackClick
                            ) {

                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = null
                                )

                            }

                        }

                    )

                }

            ) { padding ->

                Column(

                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .verticalScroll(
                            rememberScrollState()
                        )

                ) {

                    Box {

                        AsyncImage(

                            model = recipe.imageUrl,

                            contentDescription = null,

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp),

                            contentScale = ContentScale.Crop

                        )

                        FilledIconButton(

                            onClick = {

                                viewModel.onIntent(
                                    DetailIntent.ToggleFavorite
                                )

                            },

                            modifier = Modifier
                                .align(
                                    Alignment.BottomEnd
                                )
                                .padding(16.dp)

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

                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(

                        text = recipe.title,

                        style =
                            MaterialTheme.typography.headlineSmall,

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )

                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    AssistChip(

                        onClick = {},

                        label = {

                            Text(
                                recipe.category
                            )

                        },

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )

                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(
                            vertical = 16.dp
                        )
                    )

                    Text(

                        text = "Instructions",

                        style =
                            MaterialTheme.typography.titleLarge,

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )

                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(

                        text = recipe.instructions,

                        style =
                            MaterialTheme.typography.bodyLarge,

                        modifier = Modifier.padding(
                            horizontal = 16.dp
                        )

                    )

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                }

            }

        }

    }

}