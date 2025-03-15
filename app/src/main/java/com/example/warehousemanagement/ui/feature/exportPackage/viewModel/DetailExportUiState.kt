package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.ImportPackages

sealed interface DetailExportUiState {
    data class Success(val detailExportPackage: ExportPackages) : DetailExportUiState
    data object Error : DetailExportUiState
    data object Loading : DetailExportUiState
}
