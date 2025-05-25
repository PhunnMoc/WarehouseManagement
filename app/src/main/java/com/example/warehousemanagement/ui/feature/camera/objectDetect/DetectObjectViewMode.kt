package com.example.warehousemanagement.ui.feature.camera.objectDetect

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mlr_apps.objectdetection.Data.DetectionObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetectObjectViewMode @Inject constructor(
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _id = savedStateHandle.getStateFlow("id", "")
    val id: StateFlow<String> = _id
    private val _quantity = savedStateHandle.getStateFlow("quantity", 0)
    val quantity: StateFlow<Int> = _quantity
}