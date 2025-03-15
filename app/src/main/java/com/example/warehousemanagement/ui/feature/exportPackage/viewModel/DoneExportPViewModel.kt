package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import androidx.lifecycle.SavedStateHandle
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
class DoneExportPViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val exportPackageUiState: StateFlow<ExportPackageUiState> = getAllExportPackage().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ExportPackageUiState.Loading
    )

    private fun getAllExportPackage(): Flow<ExportPackageUiState> =
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