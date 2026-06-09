package com.geekstudio.recipeplanner.navigation

import android.net.http.SslCertificate.saveState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun BottomBar(
    navController: NavController,
    currentRoute: String?
) {

    NavigationBar {

        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Favorites,
            BottomNavItem.Settings
        )

        items.forEach { item ->

            NavigationBarItem(
                selected =
                    currentRoute == item.route,
                onClick = {

                    navController.navigate(item.route) {

                        launchSingleTop = true

                        restoreState = true

                        popUpTo(
                            navController.graph
                                .findStartDestination()
                                .id
                        ) {
                            saveState = true
                        }

                    }

                },
                icon = {},
                label = {
                    Text(item.label)
                }
            )

        }

    }

}