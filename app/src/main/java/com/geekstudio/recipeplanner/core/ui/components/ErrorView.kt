package com.geekstudio.recipeplanner.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.geekstudio.recipeplanner.core.ui.spacing.AppSpacing

@Composable
fun ErrorView(
    message: String, onRetry: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = message)

        Spacer(modifier = Modifier.height(AppSpacing.Medium))

        Button(
            onClick = onRetry
        ) {

            Text("Retry")

        }

    }

}