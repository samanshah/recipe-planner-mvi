package com.geekstudio.recipeplanner.presentation.home.viewmodel

import app.cash.turbine.test
import com.geekstudio.recipeplanner.fake.FakeRecipeRepository
import com.geekstudio.recipeplanner.presentation.home.contract.HomeEffect
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun search_intent_should_update_query() =
        runTest {

            val viewModel =
                HomeViewModel(
                    FakeRecipeRepository()
                )

            viewModel.onIntent(
                HomeIntent.SearchRecipes(
                    "Pizza"
                )
            )

            viewModel.state.test {

                skipItems(1)

                val state =
                    awaitItem()

                assertEquals(
                    "Pizza",
                    state.searchQuery
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun search_should_update_query() =
        runTest {

            val viewModel =
                HomeViewModel(
                    FakeRecipeRepository()
                )

            viewModel.onIntent(
                HomeIntent.SearchRecipes(
                    "Pizza"
                )
            )

            advanceTimeBy(600)

            assertEquals(
                "Pizza",
                viewModel.state.value.searchQuery
            )
        }

    @Test
    fun search_query_should_be_saved_in_state() =
        runTest {

            val viewModel =
                HomeViewModel(
                    FakeRecipeRepository()
                )

            viewModel.onIntent(
                HomeIntent.SearchRecipes(
                    "Burger"
                )
            )

            advanceTimeBy(600)

            assertEquals(
                "Burger",
                viewModel.state.value.searchQuery
            )
        }

    @Test
    fun retry_should_use_last_query() =
        runTest {

            val repository =
                FakeRecipeRepository()

            val viewModel =
                HomeViewModel(
                    repository
                )

            viewModel.onIntent(
                HomeIntent.SearchRecipes(
                    "Pizza"
                )
            )

            advanceTimeBy(600)

            viewModel.onIntent(
                HomeIntent.Retry
            )

            advanceUntilIdle()

            assertEquals(
                "Pizza",
                viewModel.state.value.lastQuery
            )
        }

    @Test
    fun recipe_click_should_emit_navigation_effect() =
        runTest {

            val viewModel =
                HomeViewModel(
                    FakeRecipeRepository()
                )

            viewModel.onIntent(
                HomeIntent.RecipeClicked(
                    "123"
                )
            )

            viewModel.effect.test {

                val effect =
                    awaitItem()

                assert(
                    effect is HomeEffect.NavigateToDetail
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
}