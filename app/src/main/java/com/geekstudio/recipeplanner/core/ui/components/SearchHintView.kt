package com.geekstudio.recipeplanner.core.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekstudio.recipeplanner.core.ui.spacing.AppSpacing

@Composable
fun SearchHintView() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Text(
                text = "Search Recipes"
            )

            Spacer(
                modifier =
                    Modifier.height(AppSpacing.Small)
            )

            Text(
                text =
                    "Start typing to discover meals"
            )

        }

    }

}