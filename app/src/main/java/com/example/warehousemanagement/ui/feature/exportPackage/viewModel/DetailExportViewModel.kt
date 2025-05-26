package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.KEY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailExportViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
    val preferencesRepository: PreferencesRepository,
) : ViewModel() {
    val detailExportPackageUiState: StateFlow<DetailExportUiState> = getAllExportPackage().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DetailExportUiState.Loading
    )

    val currentUser: StateFlow<User> = preferencesRepository.getUserId().map {
        wareHouseRepository.getUserDetails(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = User("", "", null)
    )


    private fun getAllExportPackage(): Flow<DetailExportUiState> =
        savedStateHandle.getStateFlow(KEY_ID, "")
            .map {
                print("IRIS $it")
                wareHouseRepository.getExportPackageById(id = it)
            }.asResult()
            .map { detailExportPackages ->
                when (detailExportPackages) {
                    is Result.Success -> {
                        DetailExportUiState.Success(detailExportPackage = detailExportPackages.data)
                    }

                    is Result.Error -> DetailExportUiState.Error
                    is Result.Loading -> DetailExportUiState.Loading
                }
            }

    fun updateExportPackage() {
        val id = savedStateHandle.get<String>(KEY_ID)
        viewModelScope.launch {
            wareHouseRepository.approveExportPackage(
                id = id ?: "",
            )
        }
    }
}