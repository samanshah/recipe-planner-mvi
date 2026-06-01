package com.geekstudio.recipeplanner.presentation.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geekstudio.recipeplanner.presentation.home.component.RecipeCard
import com.geekstudio.recipeplanner.presentation.home.contract.HomeEffect
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import com.geekstudio.recipeplanner.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
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

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->

            when (effect) {

                is HomeEffect.ShowSnackbar -> {

                    snackbarHostState
                        .showSnackbar(
                            effect.message
                        )

                }

                is HomeEffect.NavigateToDetail -> {

                }

                is HomeEffect.ScrollToTop -> {
                    listState.animateScrollToItem(0)
                }
            }

        }

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
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
                modifier = Modifier.fillMaxWidth()
            )

            if (state.isLoading) {

                CircularProgressIndicator()

            }

            state.error?.let {

                Text(text = it)

            }

            LazyColumn {

                items(state.recipes) { recipe ->

                    RecipeCard(
                        recipe,
                        onClick = {

                        },
                        onFavoriteClick = {

                        }
                    )
                }

            }

        }

    }

}