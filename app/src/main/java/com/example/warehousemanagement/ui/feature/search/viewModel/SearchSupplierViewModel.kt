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

val SEARCH_SUPPLIER_QUERY = "idSupplier"

@HiltViewModel
class SearchSupplierViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val saveStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchSupplierUiState: StateFlow<SearchSupplierUiState> =
        searchSupplierResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchSupplierUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchSupplierResult(): Flow<SearchSupplierUiState> =
        saveStateHandle.getStateFlow(SEARCH_SUPPLIER_QUERY, "").debounce(500)
            .map { wareHouseRepository.getSearchedSupplierByName(it) }.asResult()
            .map { listSupplier ->
                when (listSupplier) {
                    is Result.Success -> {
                        SearchSupplierUiState.Success(listSupplier = listSupplier.data)
                    }

                    is Result.Error -> SearchSupplierUiState.Error
                    is Result.Loading -> SearchSupplierUiState.Loading
                }
            }

    fun onChangeSearchQuery(query: String) {
        saveStateHandle[SEARCH_SUPPLIER_QUERY] = query
    }

}