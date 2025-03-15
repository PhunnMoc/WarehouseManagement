package com.example.warehousemanagement.ui.feature.supplier.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSupplierViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {

    fun addNewSupplier(supplier: Supplier) {
        try {
            viewModelScope.launch {
                val response = wareHouseRepository.addNewSupplier(supplier = supplier)
            }
        } catch (e: Exception) {
            println("Iriss $e")
        }
    }
}