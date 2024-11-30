package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Supplier

sealed interface SearchSupplierUiState {
    data class Success(val listSuggestionSupplier: List<Supplier>) : SearchSupplierUiState
    data object Error : SearchSupplierUiState
    data object Loading : SearchSupplierUiState
}
