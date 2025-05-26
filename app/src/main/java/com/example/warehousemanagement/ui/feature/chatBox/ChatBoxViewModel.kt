package com.example.warehousemanagement.ui.feature.chatBox

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBoxViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _resultChatBox =
        MutableStateFlow<ChatBoxUiState>(ChatBoxUiState.Success(message = ""))
    val resultChatBox: StateFlow<ChatBoxUiState> = _resultChatBox.asStateFlow()
    fun getAnswerChatBox(question: String) {
        viewModelScope.launch {
            try {
                val result =
                    runCatching { wareHouseRepository.getAnswerChatBox(question) }.asResult()
                _resultChatBox.value = when (result) {
                    Result.Loading -> ChatBoxUiState.Loading
                    is Result.Error -> ChatBoxUiState.Error
                    is Result.Success -> ChatBoxUiState.Success(
                        message = result.data
                    )

                }
            } catch (e: Exception) {
                _resultChatBox.value = ChatBoxUiState.Error
                // Optional: Log error for debugging
                Log.e("ChatBoxViewModel", "Error getting answer: ${e.message}", e)
            }
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