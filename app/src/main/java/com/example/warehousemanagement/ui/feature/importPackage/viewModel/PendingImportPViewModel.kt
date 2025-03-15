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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

//@HiltViewModel
//class PendingImportPViewModel @Inject constructor(
//    private val wareHouseRepository: WareHouseRepository,
//    private val savedStateHandle: SavedStateHandle,
//) : ViewModel() {
//    val importPackageUiState: StateFlow<ImportPackageUiState> = getAllImportPackage().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = ImportPackageUiState.Loading
//    )
//
//    private fun getAllImportPackage(): Flow<ImportPackageUiState> =
//        flow { emit(wareHouseRepository.getPendingImportPackages()) }.asResult()
//            .map { importPackages ->
//                when (importPackages) {
//                    is Result.Success -> {
//                        ImportPackageUiState.Success(importPackages = importPackages.data.reversed())
//                    }
//
//                    is Result.Error -> ImportPackageUiState.Error
//                    is Result.Loading -> ImportPackageUiState.Loading
//                }
//            }
//}