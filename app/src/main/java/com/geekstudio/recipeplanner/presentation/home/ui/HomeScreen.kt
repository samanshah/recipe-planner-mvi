package com.geekstudio.recipeplanner.presentation.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.geekstudio.recipeplanner.core.ui.components.EmptyState
import com.geekstudio.recipeplanner.core.ui.components.ErrorView
import com.geekstudio.recipeplanner.core.ui.components.SearchHintView
import com.geekstudio.recipeplanner.core.ui.loading.RecipeSkeleton
import com.geekstudio.recipeplanner.core.ui.spacing.AppSpacing
import com.geekstudio.recipeplanner.navigation.Screen
import com.geekstudio.recipeplanner.presentation.home.component.RecipeCard
import com.geekstudio.recipeplanner.presentation.home.component.SearchHistorySection
import com.geekstudio.recipeplanner.presentation.home.contract.HomeEffect
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import com.geekstudio.recipeplanner.presentation.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    ) {

    val state by viewModel.state
        .collectAsStateWithLifecycle()

//    val pullRefreshState =
//        rememberPullRefreshState(
//            refreshing = state.isLoading,
//            onRefresh = {
//
//                viewModel.onIntent(
//                    HomeIntent.Refresh
//                )
//
//            }
//        )

    val pullRefreshState = rememberPullToRefreshState()

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                is HomeEffect.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        effect.message
                    )

                }

                is HomeEffect.NavigateToDetail -> {

                    navController.navigate(
                        Screen.Detail.createRoute(
                            effect.recipeId
                        )
                    )

                }

                HomeEffect.ScrollToTop -> {

                    listState.animateScrollToItem(
                        0
                    )

                }

            }

        }

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.padding(AppSpacing.Medium)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            AnimatedVisibility(
                visible = !state.isConnected
            ) {

                Surface {

                    Text(
                        text = "No Internet Connection"
                    )

                }

            }

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onIntent(
                        HomeIntent.SearchRecipes(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Search")
                }
            )

            if (
                state.searchQuery.isBlank() &&
                state.searchHistory.isNotEmpty()
            ) {

                SearchHistorySection(
                    history = state.searchHistory,

                    onItemClick = {

                        viewModel.onIntent(
                            HomeIntent.HistoryClicked(it)
                        )

                    },

                    onDeleteClick = {

                        viewModel.onIntent(
                            HomeIntent.DeleteHistory(it)
                        )

                    },

                    onClearAll = {

                        viewModel.onIntent(
                            HomeIntent.ClearHistory
                        )

                    }

                )

            }

            when {
                state.isLoading -> {
                    RecipeSkeleton()
                }

                state.error != null -> {
                    ErrorView(
                        message = state.error ?: "",
                        onRetry = {

                            viewModel.onIntent(
                                HomeIntent.Retry
                            )

                        }
                    )
                }

                state.showSearchHint -> {

                    SearchHintView()

                }

                state.recipes.isEmpty() &&
                state.hasSearched -> {
                    EmptyState(
                        "No Recipes Found"
                    )
                }

                state.recipes.isEmpty() -> {

                    EmptyState(
                        message = "No Recipes Found"
                    )

                }

                else -> {
                    PullToRefreshBox(
                        isRefreshing = state.isLoading,
                        state = pullRefreshState,
                        onRefresh = {

                            viewModel.onIntent(
                                HomeIntent.Refresh
                            )

                        }
                    ) {
                        LazyColumn(
                            state = listState,
                            contentPadding = PaddingValues(bottom = 96.dp)
                        ) {

                            items(state.recipes) { recipe ->

                                Spacer(modifier = Modifier.height(AppSpacing.Medium))

                                RecipeCard(
                                    recipe = recipe,
                                    onClick = {
                                        viewModel.onIntent(
                                            HomeIntent.RecipeClicked(
                                                recipe.id
                                            )
                                        )
                                    },
                                    onFavoriteClick = {
                                        viewModel.onIntent(
                                            HomeIntent.ToggleFavorite(
                                                recipe.id
                                            )
                                        )
                                    }
                                )
                            }

                        }
                    }
                }
            }

        }

    }

}