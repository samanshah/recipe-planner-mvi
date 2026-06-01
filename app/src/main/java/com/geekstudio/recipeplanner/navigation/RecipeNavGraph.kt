package com.geekstudio.recipeplanner.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekstudio.recipeplanner.presentation.favorites.ui.FavoritesScreen
import com.geekstudio.recipeplanner.presentation.home.ui.HomeScreen
import com.geekstudio.recipeplanner.presentation.settings.ui.SettingsScreen

@Composable
fun RecipeNavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen()

        }

        composable("favorites") {

            FavoritesScreen()

        }

        composable("settings") {

            SettingsScreen()

        }

    }

}