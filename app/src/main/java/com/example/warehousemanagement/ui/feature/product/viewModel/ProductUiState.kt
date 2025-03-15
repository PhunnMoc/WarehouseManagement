package com.example.warehousemanagement.ui.feature.product.viewModel

import com.example.warehousemanagement.domain.model.Product


sealed interface ProductUiState {
    data class Success(
        val listProduct: List<Product>
    ) : ProductUiState

    data object Error : ProductUiState
    data object Loading : ProductUiState
}

data class ProductFilterUiState(
    val filter: Map<String, String>,
)



