package com.example.warehousemanagement.ui.feature.user.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailProductUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_PRODUCT_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ManagerUserViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
) : ViewModel() {

    val managerUserUiState: StateFlow<ManagerUserUiState> =
        getAllUser()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ManagerUserUiState.Loading
            )

    private fun getAllUser(): Flow<ManagerUserUiState> =
        flow { emit(wareHouseRepository.getAllUserDetails()) }.asResult()
            .map { listUser ->
                when (listUser) {
                    is Result.Success -> {
                        ManagerUserUiState.Success(listUser = listUser.data)
                    }

                    is Result.Error -> ManagerUserUiState.Error
                    is Result.Loading -> ManagerUserUiState.Loading
                }
            }
}