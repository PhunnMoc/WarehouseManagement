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

val SEARCH_STORAGE_LOCATION_QUERY_NAME = "name_storage"

@HiltViewModel
class SearchStorageLocationViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val saveStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchStorageLocationUiState: StateFlow<SearchStorageLocationUiState> =
        searchStorageLocationResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchStorageLocationUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchStorageLocationResult(): Flow<SearchStorageLocationUiState> =
        saveStateHandle.getStateFlow(SEARCH_STORAGE_LOCATION_QUERY_NAME, "").debounce(500)
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

    fun onChangeSearchQuery(query: String) {
        saveStateHandle[SEARCH_STORAGE_LOCATION_QUERY_NAME] = query
    }

}