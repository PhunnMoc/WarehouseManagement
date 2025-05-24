package com.example.warehousemanagement.ui.feature.supplier.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSupplierViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun addNewSupplier(supplier: Supplier) {
        viewModelScope.launch {
            runCatching {
                wareHouseRepository.addNewSupplier(supplier = supplier)
            }.onSuccess {
                _message.value = "Adding a new Supplier successfully"
            }.onFailure { e ->
                _message.value = "Adding a new Supplier failed, please check again"
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}