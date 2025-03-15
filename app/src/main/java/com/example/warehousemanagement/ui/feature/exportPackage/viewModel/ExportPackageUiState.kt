package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import com.example.warehousemanagement.domain.model.ExportPackages

sealed interface ExportPackageUiState {
    data class Success(val exportPackages: List<ExportPackages>) : ExportPackageUiState
    data object Error : ExportPackageUiState
    data object Loading : ExportPackageUiState
}
