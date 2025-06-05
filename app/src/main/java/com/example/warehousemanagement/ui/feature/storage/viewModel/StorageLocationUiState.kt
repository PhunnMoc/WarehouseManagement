package com.example.warehousemanagement.ui.feature.storage.viewModel

import com.example.warehousemanagement.data.network.dto.DetailStorageResponse
import com.example.warehousemanagement.domain.model.StorageLocation

sealed interface StorageLocationUiState {
    data class Success(val listStorageLocation: List<StorageLocation>) : StorageLocationUiState
    data object Error : StorageLocationUiState
    data object Loading : StorageLocationUiState
}

sealed interface DetailStorageLocationUiState {
    data class Success(val detailStorageLocation: DetailStorageResponse) :
        DetailStorageLocationUiState

    data object Error : DetailStorageLocationUiState
    data object Loading : DetailStorageLocationUiState
}