package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Customer

sealed interface SearchCustomerUiState {
    data class Success(val listSuggestionCustomer: List<Customer>) : SearchCustomerUiState
    data object Error : SearchCustomerUiState
    data object Loading : SearchCustomerUiState
}
