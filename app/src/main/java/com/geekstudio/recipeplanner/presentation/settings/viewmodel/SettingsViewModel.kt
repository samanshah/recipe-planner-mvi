package com.geekstudio.recipeplanner.presentation.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekstudio.recipeplanner.data.preferences.DataStoreManager
import com.geekstudio.recipeplanner.presentation.settings.contract.SettingsIntent
import com.geekstudio.recipeplanner.presentation.settings.contract.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())

    val state = _state.asStateFlow()

    init {

        observeDarkMode()

    }

    fun onIntent(
        intent: SettingsIntent
    ) {

        when (intent) {

            is SettingsIntent.ToggleDarkMode -> {

                viewModelScope.launch {

                    dataStoreManager.setDarkMode(
                        intent.enabled
                    )

                }

            }

        }

    }

    private fun observeDarkMode() {

        viewModelScope.launch {

            dataStoreManager.darkModeFlow.collectLatest { enabled ->

                _state.update {

                    it.copy(
                        darkModeEnabled = enabled
                    )

                }

            }

        }

    }

}