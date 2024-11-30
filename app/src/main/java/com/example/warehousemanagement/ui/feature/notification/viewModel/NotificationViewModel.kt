package com.example.warehousemanagement.ui.feature.notification.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.WebSocketManager
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val preferencesRepository: PreferencesRepository,
    private val webSocketManager: WebSocketManager,
) : ViewModel() {

    val notificationUiState: StateFlow<NotificationUiState> = getNotification().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NotificationUiState.Loading
    )

    private fun getNotification(): Flow<NotificationUiState> =
        flow { emit(wareHouseRepository.getAllNotificationDetails()) }.asResult()
            .map { notification ->
                when (notification) {
                    is Result.Success -> {
                        NotificationUiState.Success(notifications = notification.data)
                    }

                    is Result.Error -> NotificationUiState.Error
                    is Result.Loading -> NotificationUiState.Loading
                }
            }
}