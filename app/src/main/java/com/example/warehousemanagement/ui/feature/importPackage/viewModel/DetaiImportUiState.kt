package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import com.example.warehousemanagement.domain.model.ImportPackages

sealed interface DetailImportUiState {
    data class Success(val detailImportPackage: ImportPackages) : DetailImportUiState
    data object Error : DetailImportUiState
    data object Loading : DetailImportUiState
}
