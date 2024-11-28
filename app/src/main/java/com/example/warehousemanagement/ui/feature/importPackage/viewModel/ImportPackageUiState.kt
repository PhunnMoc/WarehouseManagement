package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product

sealed interface ImportPackageUiState {
    data class Success(val importPackages: List<ImportPackages>) : ImportPackageUiState
    data object Error : ImportPackageUiState
    data object Loading : ImportPackageUiState
}
