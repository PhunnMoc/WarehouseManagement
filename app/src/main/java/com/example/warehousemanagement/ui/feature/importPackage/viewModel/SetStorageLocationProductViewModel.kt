package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetStorageLocationProductViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _mapProductAndStorage = MutableStateFlow<Map<String, String>>(emptyMap())
    val mapProductAndStorage: StateFlow<Map<String, String>> = _mapProductAndStorage

    fun addProductAndStorage(key: String, value: String) {
        viewModelScope.launch {
            _mapProductAndStorage.value = _mapProductAndStorage.value.toMutableMap().apply {
                this[key] = value
            }
            // print("IRIS FILTER ${_filter.value}")
        }
    }

    fun updateAllProduct() {
        val id = savedStateHandle.get<String>(KEY_ID)
        viewModelScope.launch {
            wareHouseRepository.updateProductsInImportPackage(
                id = id ?: "",
                storageLocationIds = _mapProductAndStorage.value
            )
        }
    }

    val listPendingProduct: StateFlow<SetStorageLocationProductUiState> =
        getAllPendingProduct().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SetStorageLocationProductUiState.Loading
        )

    private fun getAllPendingProduct(): Flow<SetStorageLocationProductUiState> =
        savedStateHandle.getStateFlow(KEY_ID, "")
            .map {
                delay(2000)
                wareHouseRepository.getImportPackageById(id = it)
            }.asResult()
            .map { importPackage ->
                when (importPackage) {
                    is Result.Success -> {
                        SetStorageLocationProductUiState.Success(listProductPending = importPackage.data.listProducts)
                    }

                    is Result.Error -> SetStorageLocationProductUiState.Error
                    is Result.Loading -> SetStorageLocationProductUiState.Loading
                }
            }
}