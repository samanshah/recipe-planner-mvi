package com.geekstudio.recipeplanner.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import com.geekstudio.recipeplanner.presentation.detail.contract.DetailIntent
import com.geekstudio.recipeplanner.presentation.detail.contract.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RecipeRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val recipeId = checkNotNull(
        savedStateHandle.get<String>(
            "recipeId"
        )
    )

    private val _state = MutableStateFlow(
        DetailState()
    )

    val state = _state.asStateFlow()

    init {

        loadRecipe()

    }

    private fun loadRecipe() {

        viewModelScope.launch {

            val recipe = repository.getRecipeById(
                recipeId
            )

            _state.update {

                it.copy(
                    isLoading = false, recipe = recipe
                )

            }

        }

    }

    fun onIntent(
        intent: DetailIntent
    ) {

        when (intent) {

            DetailIntent.ToggleFavorite -> {

                toggleFavorite()

            }

        }

    }

    private fun toggleFavorite() {

        viewModelScope.launch {

            val recipe = state.value.recipe ?: return@launch

            val isFavorite = repository.isFavorite(
                recipe.id
            )

            if (isFavorite) {

                repository.removeFavorite(
                    recipe.id
                )

            } else {

                repository.addFavorite(
                    recipe
                )

            }

            loadRecipe()

        }

    }

}