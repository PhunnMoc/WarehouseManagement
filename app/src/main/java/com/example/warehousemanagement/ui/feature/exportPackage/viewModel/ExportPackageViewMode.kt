package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_CUSTOMER_QUERY
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_PRODUCT_QUERY_NAME
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerUiState
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
import kotlinx.coroutines.launch
import javax.inject.Inject

val SEARCH_CUSTOMER_ID = "id"

@HiltViewModel
class ExportPackageViewMode @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {
    val roleUiState: StateFlow<Boolean> =
        preferencesRepository.getUserRole().map { it == "ADMIN" }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = false,
        )

    val customerUiState: StateFlow<Customer?> =
        savedStateHandle.getStateFlow(SEARCH_CUSTOMER_ID, "")
            .map { wareHouseRepository.getCustomerById(it) }.asResult()
            .map { customer ->
                when (customer) {
                    is Result.Success -> {
                        customer.data
                    }

                    is Result.Loading -> null
                    is Result.Error -> null
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    fun onChangeCustomerIdQuery(query: String) {
        viewModelScope.launch {
            savedStateHandle[SEARCH_CUSTOMER_ID] = query
        }
    }

    val exportPackagePendingUiState: StateFlow<ExportPackageUiState> =
        getAllExportPendingPackage().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExportPackageUiState.Loading
        )

    private fun getAllExportPendingPackage(): Flow<ExportPackageUiState> =
        flow { emit(wareHouseRepository.getPendingExportPackages()) }.asResult()
            .map { exportPackages ->
                when (exportPackages) {
                    is Result.Success -> {
                        ExportPackageUiState.Success(exportPackages = exportPackages.data)
                    }

                    is Result.Error -> ExportPackageUiState.Error
                    is Result.Loading -> ExportPackageUiState.Loading
                }
            }

    val exportPackageDoneUiState: StateFlow<ExportPackageUiState> =
        getAllExportDonePackage().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ExportPackageUiState.Loading
        )

    private fun getAllExportDonePackage(): Flow<ExportPackageUiState> =
        flow { emit(wareHouseRepository.getDoneExportPackages()) }.asResult()
            .map { exportPackages ->
                when (exportPackages) {
                    is Result.Success -> {
                        ExportPackageUiState.Success(exportPackages = exportPackages.data)
                    }

                    is Result.Error -> ExportPackageUiState.Error
                    is Result.Loading -> ExportPackageUiState.Loading
                }
            }
}