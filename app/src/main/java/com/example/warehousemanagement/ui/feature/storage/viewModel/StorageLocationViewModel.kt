package com.example.warehousemanagement.ui.feature.storage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StorageLocationViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {
    val roleUiState: StateFlow<Boolean> =
        preferencesRepository.getUserRole().map { it == "ADMIN" }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = false,
        )

    val storageLocationUiState: StateFlow<StorageLocationUiState> =
        getAllStorageLocation()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = StorageLocationUiState.Loading
            )

    private fun getAllStorageLocation(): Flow<StorageLocationUiState> =
        flow { emit(wareHouseRepository.getAllStoLocDetails()) }.asResult()
            .map { listStorageLocation ->
                when (listStorageLocation) {
                    is Result.Success -> {
                        StorageLocationUiState.Success(listStorageLocation = listStorageLocation.data)
                    }

                    is Result.Error -> StorageLocationUiState.Error
                    is Result.Loading -> StorageLocationUiState.Loading
                }
            }
}