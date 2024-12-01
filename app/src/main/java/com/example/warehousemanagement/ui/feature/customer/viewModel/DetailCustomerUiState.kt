package com.example.warehousemanagement.ui.feature.product.viewModel

import com.example.warehousemanagement.domain.model.Customer

sealed interface DetailCustomerUiState {
    data class Success(val customer: Customer) : DetailCustomerUiState
    data object Error : DetailCustomerUiState
    data object Loading : DetailCustomerUiState
}
