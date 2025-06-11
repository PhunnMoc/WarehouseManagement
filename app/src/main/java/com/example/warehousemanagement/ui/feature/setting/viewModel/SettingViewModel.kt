package com.example.warehousemanagement.ui.feature.setting.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.WebSocketManager
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.Information
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val preferencesRepository: PreferencesRepository,
    val savedStateHandle: SavedStateHandle,
    private val webSocketManager: WebSocketManager,
) : ViewModel() {

    val informationUiState: StateFlow<InformationUiState> = getInformation().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = InformationUiState.Loading
    )

    private fun getInformation(): Flow<InformationUiState> =
        savedStateHandle.getStateFlow("id", "")
            .map {
                it.ifBlank {
                    preferencesRepository.getUserId().first()
                }
            }.map { wareHouseRepository.getUserDetails(it) }.asResult()
            .map { user ->
                when (user) {
                    is Result.Success -> {
                        InformationUiState.Success(user = user.data)
                    }

                    is Result.Error -> InformationUiState.Error
                    is Result.Loading -> InformationUiState.Loading
                }
            }

    fun updateInformation(
        id: String,
        user: User,
    ) {
        viewModelScope.launch { wareHouseRepository.updateUserDetail(id = id, user = user) }
        getInformation()
    }

    fun logout() {
        viewModelScope.launch {
            preferencesRepository.deleteUser()
            // webSocketManager.disconnect()
        }
    }
}
