package com.geekstudio.recipeplanner.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.geekstudio.recipeplanner.core.ui.typography.AppTypography

@Composable
fun RecipePlannerTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            if (darkTheme)
                darkColorScheme()
            else
                lightColorScheme(),

        typography = AppTypography,

        content = content

    )

}