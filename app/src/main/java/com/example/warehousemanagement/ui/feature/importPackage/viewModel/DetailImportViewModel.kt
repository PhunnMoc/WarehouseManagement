package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

val KEY_ID = "id"

@HiltViewModel
class DetailImportViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val detailImportPackageUiState: StateFlow<DetailImportUiState> = getAllImportPackage().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DetailImportUiState.Loading
    )

    private fun getAllImportPackage(): Flow<DetailImportUiState> =
        savedStateHandle.getStateFlow(KEY_ID, "")
            .map {
                print("IRIS $it")
                wareHouseRepository.getPendingImportPackageById(id = it)
            }.asResult()
            .map { detailImportPackages ->
                when (detailImportPackages) {
                    is Result.Success -> {
                        DetailImportUiState.Success(detailImportPackage = detailImportPackages.data)
                    }

                    is Result.Error -> DetailImportUiState.Error
                    is Result.Loading -> DetailImportUiState.Loading
                }
            }

    fun updateImportPackage(status: String) {
        val id = savedStateHandle.get<String>(KEY_ID)
        viewModelScope.launch {
            wareHouseRepository.updateImportPackage(
                id = id ?: "",
                status = status,
            )
        }
    }

}