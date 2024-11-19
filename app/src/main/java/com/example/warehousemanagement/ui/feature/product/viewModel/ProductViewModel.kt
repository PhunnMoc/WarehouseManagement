package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
        flow { emit(wareHouseRepository.getAllProducts()) }
            .map { ProductUiState(listProduct = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ProductUiState()
            )
}