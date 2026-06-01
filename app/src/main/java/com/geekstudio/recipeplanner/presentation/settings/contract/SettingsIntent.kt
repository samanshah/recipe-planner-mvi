package com.geekstudio.recipeplanner.presentation.settings.contract

sealed interface SettingsIntent {

    data class ToggleDarkMode(
        val enabled: Boolean
    ) : SettingsIntent

}