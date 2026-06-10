package com.geekstudio.recipeplanner.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchHistorySection(
    history: List<String>,
    onItemClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    onClearAll: () -> Unit
) {

    Column {

        Text(
            text = "Recent Searches",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        FlowRow(
            horizontalArrangement =
                Arrangement.spacedBy(8.dp),
            verticalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            history.forEach { query ->

                AssistChip(
                    onClick = {
                        onItemClick(query)
                    },
                    label = {
                        Text(query)
                    }
                )
//                InputChip(
//                    label = {
//                        Text(query)
//                    },
//                    onClick = {
//                        onDeleteClick(query)
//                    },
//                    selected = false,
//                    trailingIcon = {
//                    Icon(
//                        imageVector =
//                            Icons.Default.Close,
//                        contentDescription = null
//                    )
//                })

            }

        }

    }

}