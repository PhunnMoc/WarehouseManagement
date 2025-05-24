package com.example.warehousemanagement.ui.feature.supplier.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_SUPPLIER_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSupplierViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val detailSupplierUiState: StateFlow<DetailSupplierUiState> =
        detailSupplier()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailSupplierUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun detailSupplier(): Flow<DetailSupplierUiState> =
        savedStateHandle.getStateFlow(SEARCH_SUPPLIER_QUERY, "")
            .map { wareHouseRepository.getSupplierById(it) }.asResult()
            .map { detailSupplier ->
                when (detailSupplier) {
                    is Result.Success -> {
                        DetailSupplierUiState.Success(supplier = detailSupplier.data)
                    }

                    is Result.Error -> DetailSupplierUiState.Error
                    is Result.Loading -> DetailSupplierUiState.Loading
                }
            }

    fun updateNewSupplier(supplier: Supplier) {
        viewModelScope.launch {
            wareHouseRepository.updateSupplier(
                id = supplier.idSupplier,
                supplier = supplier,
            )
        }
    }
}
