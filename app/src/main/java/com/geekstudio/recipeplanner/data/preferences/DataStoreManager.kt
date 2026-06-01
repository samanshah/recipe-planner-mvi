package com.geekstudio.recipeplanner.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

class DataStoreManager(
    private val context: Context
) {

    val darkModeFlow: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[
                PreferenceKeys.DARK_MODE
            ] ?: false

        }

    suspend fun setDarkMode(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[
                PreferenceKeys.DARK_MODE
            ] = enabled

        }

    }

}