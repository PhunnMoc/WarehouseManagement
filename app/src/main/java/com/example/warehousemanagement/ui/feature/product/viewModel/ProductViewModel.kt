package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {
    private val _filter = MutableStateFlow<Map<String, String>>(emptyMap())
    val filter: StateFlow<Map<String, String>> = _filter

    @OptIn(ExperimentalCoroutinesApi::class)
    val productUiState: StateFlow<ProductUiState> =
        _filter.flatMapLatest { currentFilter ->
            getAllProduct(currentFilter)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductUiState.Loading
        )

    private fun getAllProduct(currentFilter: Map<String, String>): Flow<ProductUiState> =
        flow {
            val products = if (currentFilter.isEmpty()) {
                wareHouseRepository.getAllProducts()
            } else {
                wareHouseRepository.getFilteredProductsDetails(currentFilter)
            }
            emit(products)
        }
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> ProductUiState.Success(listProduct = result.data)
                    is Result.Error -> ProductUiState.Error
                    is Result.Loading -> ProductUiState.Loading
                }
            }

    fun addFilterMap(key: String, value: String) {
        viewModelScope.launch {
            _filter.value = _filter.value.toMutableMap().apply {
                this[key] = value
            }
           // print("IRIS FILTER ${_filter.value}")
        }
    }

    fun clearFilter() {
        viewModelScope.launch {
            _filter.value = emptyMap()
        }
    }


}