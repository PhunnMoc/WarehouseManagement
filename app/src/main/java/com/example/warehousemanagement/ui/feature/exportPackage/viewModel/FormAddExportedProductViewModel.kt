package com.example.warehousemanagement.ui.feature.exportPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.ExportPackagePendingDto
import com.example.warehousemanagement.data.network.dto.ExportProductDto
import com.example.warehousemanagement.data.network.dto.ReceiverResponse
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_CUSTOMER_QUERY
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_PRODUCT_QUERY
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_PRODUCT_QUERY_NAME
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormAddExportedProductViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    val idSender = preferencesRepository.getUserId()
    fun addPackage(
        date: String,
        note: String,
        customerId: String,
        packageName: String,
        listExportProducts: List<ExportProductDto>,
    ) {
        viewModelScope.launch {
            try {
                idSender.collect { idSender ->
                    wareHouseRepository.addPendingExportPackages(
                        pendingExportPackage = ExportPackagePendingDto(
                            customerId = customerId,
                            deliveryMethod = "",
                            idSender = idSender,
                            listProducts = listExportProducts,
                            note = note,
                            packageName = packageName,
                        )
                    )
                }
            } catch (e: Exception) {
                println("HUHU $e")
            }
        }
    }


    val searchProductUiState: StateFlow<SearchProductUiState> =
        searchProductResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchProductUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchProductResult(): Flow<SearchProductUiState> =
        savedStateHandle.getStateFlow(SEARCH_PRODUCT_QUERY_NAME, "").debounce(500)
            .map { wareHouseRepository.searchProductsByName(it) }.asResult()
            .map { listSuggestionProduct ->
                when (listSuggestionProduct) {
                    is Result.Success -> {
                        SearchProductUiState.Success(listSuggestionProduct = listSuggestionProduct.data)
                    }

                    is Result.Error -> SearchProductUiState.Error
                    is Result.Loading -> SearchProductUiState.Loading
                }
            }

    fun onChangeSearchProductQuery(query: String) {
        savedStateHandle[SEARCH_PRODUCT_QUERY_NAME] = query
    }
}