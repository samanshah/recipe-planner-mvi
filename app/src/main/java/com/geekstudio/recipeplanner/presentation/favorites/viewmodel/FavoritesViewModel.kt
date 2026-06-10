package com.geekstudio.recipeplanner.presentation.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekstudio.recipeplanner.domain.model.Recipe
import com.geekstudio.recipeplanner.domain.repository.RecipeRepository
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesEffect
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesIntent
import com.geekstudio.recipeplanner.presentation.favorites.contract.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private var lastRemovedRecipe: Recipe? = null

    private val _state = MutableStateFlow(
        FavoritesState()
    )

    val state = _state.asStateFlow()

    private val _effect = Channel<FavoritesEffect>(Channel.BUFFERED)

    val effect = _effect.receiveAsFlow()

    init {
        observeFavorites()
    }

    private fun observeFavorites() {

        viewModelScope.launch {

            repository.observeFavorites().collectLatest { recipes ->

                    _state.update {

                        it.copy(
                            recipes = recipes
                        )

                    }

                }

        }

    }

    fun onIntent(favoritesIntent: FavoritesIntent) {

        when (favoritesIntent) {
            is FavoritesIntent.RecipeClicked -> {
                viewModelScope.launch {
                    _effect.send(
                        FavoritesEffect.NavigateToDetail(
                            favoritesIntent.recipeId
                        )
                    )
                }
            }

            is FavoritesIntent.RemoveFavorite -> {

                removeFavorite(
                    favoritesIntent.recipeId
                )

            }

            is FavoritesIntent.LoadFavorites -> {

            }
        }

    }

    private fun removeFavorite(
        recipeId: String
    ) {

        viewModelScope.launch {

            val recipe = state.value.recipes.find {
                it.id == recipeId
            } ?: return@launch

            lastRemovedRecipe = recipe

            repository.removeFavorite(
                recipeId
            )

            _effect.send(
                FavoritesEffect.ShowUndoSnackbar(
                    recipeId
                )
            )

        }

    }

    fun undoRemoveFavorite() {

        val recipe = lastRemovedRecipe ?: return

        viewModelScope.launch {

            repository.addFavorite(
                recipe
            )

            lastRemovedRecipe = null

        }

    }
}