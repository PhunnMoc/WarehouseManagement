package com.example.warehousemanagement.ui.feature.genre.viewModel

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
class GenreViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository
) : ViewModel() {
    val genreUiState: StateFlow<GenreUiState> =
        getAllGenre()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GenreUiState.Loading
            )

    private fun getAllGenre(): Flow<GenreUiState> =
        flow { emit(wareHouseRepository.getAllGenre()) }.asResult()
            .map { listGenre ->
                when (listGenre) {
                    is Result.Success -> {
                        GenreUiState.Success(listGenre = listGenre.data)
                    }

                    is Result.Error -> GenreUiState.Error
                    is Result.Loading -> GenreUiState.Loading
                }
            }
}