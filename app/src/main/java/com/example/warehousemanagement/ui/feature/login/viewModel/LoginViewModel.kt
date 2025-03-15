package com.example.warehousemanagement.ui.feature.login.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.WebSocketManager
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

val USER_NAME = "userName"
val PASSWORD = "password"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
    private val preferencesRepository: PreferencesRepository,
    private val webSocketManager: WebSocketManager,
) : ViewModel() {


    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState


    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading // Emit loading state
            try {
                val result = wareHouseRepository.login(username, password)
                if (!result["token"].isNullOrBlank()) {
                    _loginUiState.value = LoginUiState.Success(token = result["token"])
                    preferencesRepository.setCurrentUser(result)
                    webSocketManager.connect()
                } else {
                    _loginUiState.value = LoginUiState.Error
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error
            }
        }
    }
}
