package com.example.warehousemanagement.ui.feature.notification.viewModel

import com.example.warehousemanagement.domain.model.Notification
import com.example.warehousemanagement.domain.model.User

sealed interface NotificationUiState {
    data class Success(val notification: Notification) : NotificationUiState
    data object Error : NotificationUiState
    data object Loading : NotificationUiState
}
