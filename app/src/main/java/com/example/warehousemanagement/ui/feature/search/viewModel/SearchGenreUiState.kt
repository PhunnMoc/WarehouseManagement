package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Genre

sealed interface SearchGenreUiState {
    data class Success(val listSuggestionGenre: List<Genre>) : SearchGenreUiState
    data object Error : SearchGenreUiState
    data object Loading : SearchGenreUiState
}
