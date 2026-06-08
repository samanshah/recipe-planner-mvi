package com.geekstudio.recipeplanner.presentation.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geekstudio.recipeplanner.presentation.settings.contract.SettingsIntent
import com.geekstudio.recipeplanner.presentation.settings.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {

        Row {

            Text(
                text = "Dark Mode"
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Switch(
                checked = state.darkModeEnabled, onCheckedChange = {

                    viewModel.onIntent(
                        SettingsIntent.ToggleDarkMode(it)
                    )

                })

        }

    }

}