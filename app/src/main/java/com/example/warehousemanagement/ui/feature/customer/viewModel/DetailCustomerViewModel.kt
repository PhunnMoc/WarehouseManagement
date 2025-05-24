package com.example.warehousemanagement.ui.feature.product.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_CUSTOMER_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCustomerViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val detailCustomerUiState: StateFlow<DetailCustomerUiState> =
        detailCustomer()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailCustomerUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun detailCustomer(): Flow<DetailCustomerUiState> =
        savedStateHandle.getStateFlow(SEARCH_CUSTOMER_QUERY, "")
            .map { wareHouseRepository.getCustomerById(it) }.asResult()
            .map { detailCustomer ->
                when (detailCustomer) {
                    is Result.Success -> {
                        DetailCustomerUiState.Success(customer = detailCustomer.data!!)
                    }

                    is Result.Error -> DetailCustomerUiState.Error
                    is Result.Loading -> DetailCustomerUiState.Loading
                }
            }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch {
            wareHouseRepository.updateNewCustomer(
                id = customer.idCustomer,
                customer = customer
            )
        }
    }
}