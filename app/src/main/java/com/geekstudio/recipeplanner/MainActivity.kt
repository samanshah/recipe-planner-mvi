package com.geekstudio.recipeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.geekstudio.recipeplanner.core.ui.theme.RecipePlannerTheme
import com.geekstudio.recipeplanner.data.preferences.DataStoreManager
import com.geekstudio.recipeplanner.presentation.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            DataStoreManager(this@MainActivity).darkModeFlow.collect {
                setContent {
                    RecipePlannerTheme(darkTheme = it) {
                        MainScreen()
                    }
                }
            }
        }
    }
}