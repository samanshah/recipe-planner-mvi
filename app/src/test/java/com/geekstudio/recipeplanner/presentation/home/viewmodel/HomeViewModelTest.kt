package com.geekstudio.recipeplanner.presentation.home.viewmodel

import app.cash.turbine.test
import com.geekstudio.recipeplanner.fake.FakeRecipeRepository
import com.geekstudio.recipeplanner.presentation.home.contract.HomeIntent
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `Search intent should update query`() =
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

                val state = awaitItem()

                assert(
                    state.searchQuery == "Pizza"
                )

            }

        }

}