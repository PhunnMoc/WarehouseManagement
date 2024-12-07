package com.example.warehousemanagement.ui.feature.user.viewModel

import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.User

sealed interface ManagerUserUiState {
    data class Success(
        val listUser: List<User>
    ) : ManagerUserUiState

    data object Error : ManagerUserUiState
    data object Loading : ManagerUserUiState
}
