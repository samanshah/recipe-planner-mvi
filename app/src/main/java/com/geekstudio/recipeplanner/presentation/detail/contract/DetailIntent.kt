package com.geekstudio.recipeplanner.presentation.detail.contract

sealed interface DetailIntent {

    data object ToggleFavorite : DetailIntent

}