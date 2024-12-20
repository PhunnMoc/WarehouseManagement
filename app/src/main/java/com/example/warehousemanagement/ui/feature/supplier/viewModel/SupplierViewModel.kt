package com.example.warehousemanagement.ui.feature.supplier.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.PreferencesRepository
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
class SupplierViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {
    val roleUiState: StateFlow<Boolean> =
        preferencesRepository.getUserRole().map { it == "ADMIN" }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = false,
        )

    val supplierUIState: StateFlow<SupplierUIState> =
        getAllSupplier()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SupplierUIState.Loading
            )

    private fun getAllSupplier(): Flow<SupplierUIState> =
        flow { emit(wareHouseRepository.getAllSuppliers()) }.asResult().map { listSupplier ->
            when (listSupplier) {
                is Result.Success -> {
                    SupplierUIState.Success(listSupplier = listSupplier.data)
                }

                is Result.Error -> SupplierUIState.Error
                is Result.Loading -> SupplierUIState.Loading
            }
        }
}