package com.example.warehousemanagement.ui.feature.setting.viewModel

import com.example.warehousemanagement.domain.model.Information
import com.example.warehousemanagement.domain.model.User

sealed interface InformationUiState {
    data class Success(val user: User) : InformationUiState
    data object Error : InformationUiState
    data object Loading : InformationUiState
}
