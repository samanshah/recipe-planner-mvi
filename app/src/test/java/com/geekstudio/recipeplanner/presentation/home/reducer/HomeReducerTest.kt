package com.geekstudio.recipeplanner.presentation.home.reducer

import com.geekstudio.recipeplanner.presentation.home.contract.HomePartialState
import com.geekstudio.recipeplanner.presentation.home.contract.HomeState
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeReducerTest {

    @Test
    fun `Loading state should update loading flag`() {

        val currentState =
            HomeState()

        val result =
            HomeReducer.reduce(
                currentState,
                HomePartialState.Loading
            )

        assertEquals(
            true,
            result.isLoading
        )

    }

}