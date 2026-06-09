package com.geekstudio.recipeplanner.presentation.home.reducer

import com.geekstudio.recipeplanner.domain.model.Recipe
import com.geekstudio.recipeplanner.presentation.home.contract.HomePartialState
import com.geekstudio.recipeplanner.presentation.home.contract.HomeState
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeReducerTest {

    @Test
    fun `Loading state should update loading flag`() {

        val currentState = HomeState()

        val result = HomeReducer.reduce(
            currentState, HomePartialState.Loading
        )

        assertEquals(
            true, result.isLoading
        )

    }

    @Test
    fun recipes_loaded_should_update_recipes() {

        val currentState = HomeState()

        val recipes: List<Recipe> = listOf()

        val result = HomeReducer.reduce(
            currentState, HomePartialState.RecipesLoaded(
                recipes
            )
        )

        assertEquals(
            recipes, result.recipes
        )
    }

    @Test
    fun error_should_update_error_message() {

        val result = HomeReducer.reduce(
            HomeState(), HomePartialState.Error(
                "Network Error"
            )
        )

        assertEquals(
            "Network Error", result.error
        )
    }

    @Test
    fun query_changed_should_update_query() {

        val result =
            HomeReducer.reduce(
                HomeState(),
                HomePartialState.QueryChanged(
                    "Pizza"
                )
            )

        assertEquals(
            "Pizza",
            result.searchQuery
        )
    }

    @Test
    fun search_hint_should_be_updated() {

        val result =
            HomeReducer.reduce(
                HomeState(),
                HomePartialState.SearchHintChanged(
                    true
                )
            )

        assertEquals(
            true,
            result.showSearchHint
        )
    }

}