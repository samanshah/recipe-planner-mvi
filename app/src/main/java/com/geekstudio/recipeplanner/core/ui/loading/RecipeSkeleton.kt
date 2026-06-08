package com.geekstudio.recipeplanner.core.ui.loading

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekstudio.recipeplanner.core.ui.spacing.AppSpacing
import com.valentinilk.shimmer.shimmer

@Composable
fun RecipeSkeleton() {

    Column {

        repeat(5) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(AppSpacing.Small)
                    .shimmer()
            ) {}

        }

    }

}