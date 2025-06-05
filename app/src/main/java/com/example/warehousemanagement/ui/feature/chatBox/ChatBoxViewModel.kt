package com.example.warehousemanagement.ui.feature.chatBox

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.storage.viewModel.DetailStorageLocationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBoxViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val navigatMessage =  savedStateHandle.getStateFlow("message", "")
    val resultChatBox: StateFlow<ChatBoxUiState> = getAnswerChatBox().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChatBoxUiState.Success("")
    )

    fun sendMessage(message: String) {
        savedStateHandle["message"] = message
    }

    private fun getAnswerChatBox(): Flow<ChatBoxUiState> =
        savedStateHandle.getStateFlow("message", "")
            .filter { message -> message.isNotBlank() }
            .map { message -> wareHouseRepository.getAnswerChatBox(message) }.asResult()
            .map { result ->
                try {
                    when (result) {
                        Result.Loading -> ChatBoxUiState.Loading
                        is Result.Error -> ChatBoxUiState.Error
                        is Result.Success -> ChatBoxUiState.Success(
                            message = result.data
                        )
                    }
                } catch (e: Exception) {
                    ChatBoxUiState.Error
                }
            }
}

sealed class ChatBoxUiState {
    data class Success(
        val message: String,
    ) : ChatBoxUiState()

    data object Loading : ChatBoxUiState()
    data object Error : ChatBoxUiState()
}