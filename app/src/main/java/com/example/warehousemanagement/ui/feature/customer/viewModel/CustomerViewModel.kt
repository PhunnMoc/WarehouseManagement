package com.example.warehousemanagement.ui.feature.customer.viewModel

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

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {
    val customerUIState: StateFlow<CustomerUIState> =
        getAllCustomer()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CustomerUIState.Loading
            )

    private fun getAllCustomer(): Flow<CustomerUIState> =
        flow { emit(wareHouseRepository.getAllCustomers()) }.asResult().map { listCustomer->
            when (listCustomer) {
                is Result.Success -> {
                    CustomerUIState.Success(listCustomer = listCustomer.data)
                }

                is Result.Error -> CustomerUIState.Error
                is Result.Loading -> CustomerUIState.Loading
            }
        }
}