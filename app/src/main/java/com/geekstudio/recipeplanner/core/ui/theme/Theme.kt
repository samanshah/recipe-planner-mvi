package com.geekstudio.recipeplanner.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.geekstudio.recipeplanner.core.ui.colors.BackgroundDark
import com.geekstudio.recipeplanner.core.ui.colors.Primary
import com.geekstudio.recipeplanner.core.ui.colors.Secondary
import com.geekstudio.recipeplanner.core.ui.typography.AppTypography

@Composable
fun RecipePlannerTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            if (darkTheme)
                DarkColors
            else
                LightColors,

        typography = AppTypography,

        content = content

    )

}

private val LightColors =
    lightColorScheme(
        primary = Primary,
        secondary = Secondary
    )

private val DarkColors =
    darkColorScheme(
        primary = Primary,
        secondary = Secondary,
        background = BackgroundDark
    )