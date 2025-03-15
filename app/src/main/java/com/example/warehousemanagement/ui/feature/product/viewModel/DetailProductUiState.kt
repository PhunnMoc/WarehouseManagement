package com.example.warehousemanagement.ui.feature.product.viewModel

import com.example.warehousemanagement.domain.model.Product

sealed interface DetailProductUiState {
    data class Success(val product: Product) : DetailProductUiState
    data object Error : DetailProductUiState
    data object Loading : DetailProductUiState
}
