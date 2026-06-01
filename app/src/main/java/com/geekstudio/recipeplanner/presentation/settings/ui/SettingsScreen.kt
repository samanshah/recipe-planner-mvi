package com.geekstudio.recipeplanner.presentation.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

            Switch(
                checked = state.darkModeEnabled, onCheckedChange = {

                    viewModel.onIntent(
                        SettingsIntent.ToggleDarkMode(it)
                    )

                })

        }

    }

}