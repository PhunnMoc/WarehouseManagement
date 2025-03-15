package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_PRODUCT_QUERY
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val detailProductUiState: StateFlow<DetailProductUiState> =
        detailProduct()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailProductUiState.Loading
            )

    private fun detailProduct(): Flow<DetailProductUiState> =
        savedStateHandle.getStateFlow(SEARCH_PRODUCT_QUERY, "")
            .map { wareHouseRepository.getProductById(it) }.asResult()
            .map { detailProduct ->
                when (detailProduct) {
                    is Result.Success -> {
                        DetailProductUiState.Success(product = detailProduct.data)
                    }

                    is Result.Error -> DetailProductUiState.Error
                    is Result.Loading -> DetailProductUiState.Loading
                }
            }
}