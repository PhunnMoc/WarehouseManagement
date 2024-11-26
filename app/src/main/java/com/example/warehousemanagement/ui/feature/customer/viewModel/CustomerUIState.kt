package com.example.warehousemanagement.ui.feature.customer.viewModel

import com.example.warehousemanagement.domain.model.Customer

sealed interface CustomerUIState {
    data class Success(val listCustomer: List<Customer>) : CustomerUIState
    data object Error : CustomerUIState
    data object Loading : CustomerUIState
}