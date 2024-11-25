package com.example.warehousemanagement.ui.feature.genre.viewModel

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.StorageLocation


sealed interface GenreUiState {
    data class Success(val listGenre: List<Genre>) : GenreUiState
    data object Error : GenreUiState
    data object Loading : GenreUiState
}
