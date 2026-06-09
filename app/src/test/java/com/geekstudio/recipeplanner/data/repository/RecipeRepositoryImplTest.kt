package com.geekstudio.recipeplanner.data.repository

import app.cash.turbine.test
import com.geekstudio.recipeplanner.data.local.dao.RecipeDao
import com.geekstudio.recipeplanner.data.local.dao.SearchHistoryDao
import com.geekstudio.recipeplanner.data.local.entity.RecipeEntity
import com.geekstudio.recipeplanner.data.remote.api.RecipeApi
import com.geekstudio.recipeplanner.data.remote.dto.RecipeDto
import com.geekstudio.recipeplanner.data.remote.dto.RecipeResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RecipeRepositoryImplTest {

    private lateinit var api: RecipeApi

    private lateinit var recipeDao: RecipeDao

    private lateinit var searchHistoryDao: SearchHistoryDao

    private lateinit var repository: RecipeRepositoryImpl

    @Before
    fun setup() {

        api = mockk()

        recipeDao = mockk()

        searchHistoryDao = mockk()

        repository =
            RecipeRepositoryImpl(
                api,
                recipeDao,
                searchHistoryDao
            )
    }

    @Test
    fun refreshRecipes_should_save_data_into_database() =
        runTest {

            val response =
                RecipeResponseDto(
                    meals =
                        listOf(
                            RecipeDto(
                                id = "1",
                                title = "Pizza",
                                imageUrl = ""
                            )
                        )
                )

            coEvery {
                api.searchRecipes("pizza")
            } returns response

            coEvery {
                recipeDao.clearRecipes()
            } returns Unit

            coEvery {
                recipeDao.insertRecipes(any())
            } returns Unit

            coEvery {
                searchHistoryDao.insertSearchQuery(any())
            } returns Unit

            repository.refreshRecipes("pizza")

            coVerify(exactly = 1) {
                api.searchRecipes("pizza")
            }

            coVerify(exactly = 1) {
                recipeDao.clearRecipes()
            }

            coVerify(exactly = 1) {
                recipeDao.insertRecipes(any())
            }
        }

    @Test
    fun blank_query_should_not_save_history() =
        runTest {

            val response =
                RecipeResponseDto(
                    meals = emptyList()
                )

            coEvery {
                api.searchRecipes("")
            } returns response

            coEvery {
                recipeDao.clearRecipes()
            } returns Unit

            coEvery {
                recipeDao.insertRecipes(any())
            } returns Unit

            repository.refreshRecipes("")

            coVerify(exactly = 0) {

                searchHistoryDao.insertSearchQuery(
                    any()
                )

            }
        }

    @Test
    fun observeRecipes_should_emit_items() =
        runTest {

            val recipes =
                listOf(
                    RecipeEntity(
                        id = "1",
                        title = "Pizza",
                        imageUrl = "",
                        category = "",
                        instructions = "",
                        isFavorite = false
                    )
                )

            every {
                recipeDao.observeRecipes()
            } returns flowOf(recipes)

            repository.observeRecipes().test {

                val emitted =
                    awaitItem()

                assertEquals(
                    1,
                    emitted.size
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
}