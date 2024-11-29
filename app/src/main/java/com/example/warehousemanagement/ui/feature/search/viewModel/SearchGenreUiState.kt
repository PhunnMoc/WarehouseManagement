package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product

sealed interface SearchGenreUiState {
    data class Success(val listGenre: List<Genre>) : SearchGenreUiState
    data object Error : SearchGenreUiState
    data object Loading : SearchGenreUiState
}