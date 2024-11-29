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

val SEARCH_GENRE_QUERY_NAME = "name_genre"

@HiltViewModel
class SearchGenreViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
    private val saveStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchGenreUiState: StateFlow<SearchGenreUiState> =
        searchGenreResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchGenreUiState.Loading
            )

    @OptIn(FlowPreview::class)
    private fun searchGenreResult(): Flow<SearchGenreUiState> =
        saveStateHandle.getStateFlow(SEARCH_GENRE_QUERY_NAME, "").debounce(500)
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

    fun onChangeSearchQuery(query: String) {
        saveStateHandle[SEARCH_GENRE_QUERY_NAME] = query
    }

}