package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {
    val productUiState: StateFlow<ProductUiState> =
        getAllProduct()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ProductUiState.Loading
            )

    private fun getAllProduct(): Flow<ProductUiState> =
        flow { emit(wareHouseRepository.getAllProducts()) }.asResult().map { listProduct ->
            when (listProduct) {
                is Result.Success -> {
                    ProductUiState.Success(listProduct = listProduct.data)
                }

                is Result.Error -> ProductUiState.Error
                is Result.Loading -> ProductUiState.Loading
            }
        }
}