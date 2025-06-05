package com.example.warehousemanagement.ui.feature.storage.viewModel

import androidx.lifecycle.SavedStateHandle
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
class DetailStorageLocationViewmodel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val detailStorageLocation: StateFlow<DetailStorageLocationUiState> =
        getAllStorageLocation()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailStorageLocationUiState.Loading
            )

    private fun getAllStorageLocation(): Flow<DetailStorageLocationUiState> =
        savedStateHandle.getStateFlow("id", "")
            .map { wareHouseRepository.getProductsBelongStorage(it) }.asResult()
            .map { detailStorageLocation ->
                when (detailStorageLocation) {
                    is Result.Success -> {
                        DetailStorageLocationUiState.Success(detailStorageLocation = detailStorageLocation.data)
                    }

                    is Result.Error -> DetailStorageLocationUiState.Error
                    is Result.Loading -> DetailStorageLocationUiState.Loading
                }
            }
}