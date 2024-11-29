package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation

sealed interface SearchStorageLocationUiState {
    data class Success(val listSearchStorageLocation: List<StorageLocation>) :
        SearchStorageLocationUiState

    data object Error : SearchStorageLocationUiState
    data object Loading : SearchStorageLocationUiState
}
