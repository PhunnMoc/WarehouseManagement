package com.example.warehousemanagement.ui.feature.search.viewModel

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import kotlinx.coroutines.flow.Flow


sealed interface SearchProductUiState {
    data class Success(val listSuggestionProduct: List<Product>) : SearchProductUiState
    data object Error : SearchProductUiState
    data object Loading : SearchProductUiState
}
