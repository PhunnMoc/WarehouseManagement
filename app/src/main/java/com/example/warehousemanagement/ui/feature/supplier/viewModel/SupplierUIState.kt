package com.example.warehousemanagement.ui.feature.supplier.viewModel

import com.example.warehousemanagement.domain.model.Supplier

sealed interface SupplierUIState {
    data class Success(val listSupplier: List<Supplier>) : SupplierUIState
    data object Error : SupplierUIState
    data object Loading : SupplierUIState
}