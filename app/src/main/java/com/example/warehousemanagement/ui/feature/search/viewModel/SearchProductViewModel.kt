//package com.example.warehousemanagement.ui.feature.search.viewModel
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.warehousemanagement.data.util.Result
//import com.example.warehousemanagement.data.util.asResult
//import com.example.warehousemanagement.domain.model.Product
//import com.example.warehousemanagement.domain.repository.WareHouseRepository
//import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.FlowPreview
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.debounce
//import kotlinx.coroutines.flow.flatMapConcat
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.stateIn
//import javax.inject.Inject
//
//val SEARCH_PRODUCT_QUERY = "id"
//
//@HiltViewModel
//class SearchProductViewModel @Inject constructor(
//    private val wareHouseRepository: WareHouseRepository,
//    private val saveStateHandle: SavedStateHandle,
//) : ViewModel() {
//    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
//    val searchProductUiState: StateFlow<SearchProductUiState> =
//        saveStateHandle.getStateFlow(SEARCH_PRODUCT_QUERY, "").debounce(2000)
//            .flatMapConcat {
//                wareHouseRepository.searchProductsByName(it).asResult()
//                    .map { searchProducts ->
//                        when (searchProducts) {
//                            is Result.Success -> {
//                                SearchProductUiState.Success(listSuggestionProduct = searchProducts.data)
//                            }
//
//                            is Result.Error -> SearchProductUiState.Error
//                            is Result.Loading -> SearchProductUiState.Loading
//                        }
//                    }
//            }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = SearchProductUiState.Loading
//            )
//
//    fun onChangeSearchQuery(query: String) {
//        saveStateHandle[SEARCH_PRODUCT_QUERY] = query
//    }
//
//}