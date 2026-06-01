package com.geekstudio.recipeplanner.presentation.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.geekstudio.recipeplanner.navigation.BottomBar
import com.geekstudio.recipeplanner.navigation.RecipeNavGraph

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            BottomBar(
                navController, currentRoute
            )

        }

    ) { paddingValues ->

        RecipeNavGraph(
            navController = navController, paddingValues = paddingValues
        )

    }

}