package com.geekstudio.recipeplanner.navigation

sealed class BottomNavItem(
    val route: String,
    val label: String
) {

    data object Home :
        BottomNavItem(
            "home",
            "Home"
        )

    data object Favorites :
        BottomNavItem(
            "favorites",
            "Favorites"
        )

    data object Settings :
        BottomNavItem(
            "settings",
            "Settings"
        )

}