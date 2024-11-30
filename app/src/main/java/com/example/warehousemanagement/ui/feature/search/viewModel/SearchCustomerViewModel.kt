package com.example.warehousemanagement.ui.feature.search.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

val SEARCH_CUSTOMER_QUERY = "id"

@HiltViewModel
class SearchCustomerViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val saveStateHandle: SavedStateHandle,
) : ViewModel() {

    val searchCustomerUiState: StateFlow<SearchCustomerUiState> =
        searchCustomerResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchCustomerUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchCustomerResult(): Flow<SearchCustomerUiState> =
        saveStateHandle.getStateFlow(SEARCH_CUSTOMER_QUERY, "").debounce(500)
            .map { wareHouseRepository.searchCustomersByName(it) }.asResult()
            .map { listSuggestionCustomer ->
                when (listSuggestionCustomer) {
                    is Result.Success -> {
                        SearchCustomerUiState.Success(listSuggestionCustomer = listSuggestionCustomer.data)
                    }
                    is Result.Error -> SearchCustomerUiState.Error
                    is Result.Loading -> SearchCustomerUiState.Loading
                }
            }

    fun onChangeSearchQuery(query: String) {
        saveStateHandle[SEARCH_CUSTOMER_QUERY] = query
    }
}
