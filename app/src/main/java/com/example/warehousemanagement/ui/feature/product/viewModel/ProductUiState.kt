package com.example.warehousemanagement.ui.feature.product.viewModel

import com.example.warehousemanagement.domain.model.Product

data class ProductUiState(
    val listProduct: List<Product> = listOf()
)