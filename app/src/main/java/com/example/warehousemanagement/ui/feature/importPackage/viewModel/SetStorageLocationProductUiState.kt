package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product

sealed interface SetStorageLocationProductUiState {
    data class Success(val listProductPending: List<Product>) : SetStorageLocationProductUiState
    data object Error : SetStorageLocationProductUiState
    data object Loading : SetStorageLocationProductUiState
}
