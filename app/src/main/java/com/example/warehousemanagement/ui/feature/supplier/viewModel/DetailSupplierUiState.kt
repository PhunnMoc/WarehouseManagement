package com.example.warehousemanagement.ui.feature.supplier.viewModel

import com.example.warehousemanagement.domain.model.Supplier

sealed interface DetailSupplierUiState {
    data class Success(val supplier: Supplier) : DetailSupplierUiState
    data object Error : DetailSupplierUiState
    data object Loading : DetailSupplierUiState
}
