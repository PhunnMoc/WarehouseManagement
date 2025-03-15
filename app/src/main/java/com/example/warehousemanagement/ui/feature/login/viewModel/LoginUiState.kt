package com.example.warehousemanagement.ui.feature.login.viewModel

import com.example.warehousemanagement.domain.model.ImportPackages

sealed interface LoginUiState {
    data class Success(val token: String?) : LoginUiState
    data object Error : LoginUiState
    data object Loading : LoginUiState
    data object Idle : LoginUiState // Initial state
}
