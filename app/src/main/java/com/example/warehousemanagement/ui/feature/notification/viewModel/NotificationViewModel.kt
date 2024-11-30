package com.example.warehousemanagement.ui.feature.notification.viewModel

import androidx.lifecycle.ViewModel
import com.example.warehousemanagement.data.util.WebSocketManager
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val preferencesRepository: PreferencesRepository,
    private val webSocketManager: WebSocketManager,
) : ViewModel() {

}