package com.geekstudio.recipeplanner.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.geekstudio.recipeplanner.core.ui.colors.AppColors
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
        primary = AppColors.Primary,
        secondary = AppColors.Secondary,
        background = AppColors.Background,
        surface = AppColors.Surface,
        error = AppColors.Error,
    )

private val DarkColors =
    darkColorScheme(
        primary = AppColors.Primary,
        secondary = AppColors.Secondary,
        background = AppColors.DarkBackground,
        surface = AppColors.Surface,
        error = AppColors.Error,
    )