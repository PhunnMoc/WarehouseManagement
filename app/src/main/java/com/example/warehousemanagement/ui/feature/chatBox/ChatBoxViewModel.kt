package com.example.warehousemanagement.ui.feature.chatBox

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _resultChatBox = MutableStateFlow("")
    val resultChatBox: StateFlow<String> = _resultChatBox.asStateFlow()
    fun getAnswerChatBox(question: String) {
        viewModelScope.launch {
            try {
                val result = wareHouseRepository.getAnswerChatBox(question)
                _resultChatBox.value = result
            } catch (e: Exception) {
                _resultChatBox.value = "Something wrong"
                // Optional: Log error for debugging
                Log.e("ChatBoxViewModel", "Error getting answer: ${e.message}", e)
            }
        }
    }
}