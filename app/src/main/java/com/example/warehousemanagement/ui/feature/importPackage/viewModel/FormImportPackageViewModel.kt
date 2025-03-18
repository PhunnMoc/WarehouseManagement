package com.example.warehousemanagement.ui.feature.importPackage.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.ReceiverResponse
import com.example.warehousemanagement.data.util.Result
import com.example.warehousemanagement.data.util.asResult
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_GENRE_QUERY_NAME
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_STORAGE_LOCATION_QUERY_NAME
import com.example.warehousemanagement.ui.feature.search.viewModel.SEARCH_SUPPLIER_QUERY
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchGenreUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchStorageLocationUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchSupplierUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val IMPORT_PACKAGE_ID_QUERY_NAME = "id"

@HiltViewModel
class FormImportPackageViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val savedStateHandle: SavedStateHandle,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    val idImportPackages: String = savedStateHandle.get<String>(KEY_ID) ?: ""
    fun updatePackage(
        date: String,
        note: String,
        packageName: String,
        listProducts: List<Product>,
    ) {
        viewModelScope.launch {
            try {
                wareHouseRepository.updatePendingImportPackage(
                    id = idImportPackages,
                    updatedImportPackage = ImportPackages(
                        id = idImportPackages,
                        importDate = date,
                        listProducts = listProducts,
                        note = note,
                        packageName = packageName,
                        receiver = ReceiverResponse(
                            _id = preferencesRepository.getUserId().stateIn(this).value,
                            null,
                            null,
                            null
                        ),
                        statusDone = "PENDING",
                    )
                )
            } catch (_: Exception) {

            }
        }
    }

    fun addPackage(
        date: String,
        note: String,
        packageName: String,
        listProducts: List<Product>,
    ) {
        viewModelScope.launch {
            try {
                wareHouseRepository.createImportPackage(
                    importPackage = ImportPackages(
                        id = "",
                        importDate = date,
                        listProducts = listProducts,
                        note = note,
                        packageName = packageName,
                        receiver = ReceiverResponse(
                            _id = preferencesRepository.getUserId().stateIn(this).value,
                            null,
                            null,
                            null
                        ),
                        statusDone = "PENDING",
                    )
                )
            } catch (_: Exception) {

            }
        }
    }

    val searchGenreUiState: StateFlow<SearchGenreUiState> =
        searchGenreResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchGenreUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchGenreResult(): Flow<SearchGenreUiState> =
        savedStateHandle.getStateFlow(SEARCH_GENRE_QUERY_NAME, "").debounce(500)
            .map { wareHouseRepository.getSearchedGenresDetails(it) }.asResult()
            .map { listGenre ->
                when (listGenre) {
                    is Result.Success -> {
                        SearchGenreUiState.Success(listGenre = listGenre.data)
                    }

                    is Result.Error -> SearchGenreUiState.Error
                    is Result.Loading -> SearchGenreUiState.Loading
                }
            }

    fun onChangeSearchGenreQuery(query: String) {
        savedStateHandle[SEARCH_GENRE_QUERY_NAME] = query
    }

    val searchStorageLocationUiState: StateFlow<SearchStorageLocationUiState> =
        searchStorageLocationResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchStorageLocationUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchStorageLocationResult(): Flow<SearchStorageLocationUiState> =
        savedStateHandle.getStateFlow(SEARCH_STORAGE_LOCATION_QUERY_NAME, "").debounce(500)
            .map { wareHouseRepository.getSearchedStorageLocationByName(it) }.asResult()
            .map { listSearchStorageLocation ->
                when (listSearchStorageLocation) {
                    is Result.Success -> {
                        SearchStorageLocationUiState.Success(listSearchStorageLocation = listSearchStorageLocation.data)
                    }

                    is Result.Error -> SearchStorageLocationUiState.Error
                    is Result.Loading -> SearchStorageLocationUiState.Loading
                }
            }

    val searchSupplierUiState: StateFlow<SearchSupplierUiState> =
        searchSupplierResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchSupplierUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchSupplierResult(): Flow<SearchSupplierUiState> =
        savedStateHandle.getStateFlow(SEARCH_SUPPLIER_QUERY, "").debounce(500)
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

    fun onChangeSearchSupplierQuery(query: String) {
        savedStateHandle[SEARCH_SUPPLIER_QUERY] = query
    }

}

