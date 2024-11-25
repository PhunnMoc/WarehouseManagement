package com.example.warehousemanagement.ui.feature.storage.viewModel

import com.example.warehousemanagement.domain.model.StorageLocation

sealed interface StorageLocationUiState {
    data class Success(val listStorageLocation: List<StorageLocation>) : StorageLocationUiState
    data object Error : StorageLocationUiState
    data object Loading : StorageLocationUiState
}
