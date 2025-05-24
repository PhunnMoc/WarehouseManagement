package com.example.warehousemanagement.ui.feature.report.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.GenreByRangeSummaryResponse
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val RANGE = "RANGE"

@HiltViewModel
class GenreByRangeViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
) : ViewModel() {

    val _rangeUiState = MutableStateFlow<RangeUIState>(RangeUIState())
    val rangeUiState: StateFlow<RangeUIState> = _rangeUiState.asStateFlow()

    val _genreByRangeImportUiState = MutableStateFlow<List<GenreByRangeSummaryResponse>>(listOf())
    val genreByRangeSummaryImportResponse: StateFlow<List<GenreByRangeSummaryResponse>> =
        _genreByRangeImportUiState.asStateFlow()
    val _genreByRangeExportUiState = MutableStateFlow<List<GenreByRangeSummaryResponse>>(listOf())
    val genreByRangeSummaryExportResponse: StateFlow<List<GenreByRangeSummaryResponse>> =
        _genreByRangeExportUiState.asStateFlow()

    fun getGenreByRangeImport() {
        viewModelScope.launch {
            val genreByRangeSummary =
                wareHouseRepository.getGenreByRangeSummaryImport(
                    startDate = _rangeUiState.value.startRangeDate,
                    endDate = _rangeUiState.value.endRangeDate,
                    limit = 10,
                )
            _genreByRangeImportUiState.value = genreByRangeSummary
        }
    }

    fun getGenreByRangeExport() {
        viewModelScope.launch {
            val genreByRangeSummary =
                wareHouseRepository.getGenreByRangeSummaryExport(
                    startDate = _rangeUiState.value.startRangeDate,
                    endDate = _rangeUiState.value.endRangeDate,
                    limit = 10,
                )
            _genreByRangeExportUiState.value = genreByRangeSummary
        }
    }


    fun updateGenreByRange(startRangeDate: Long?, endRangeDate: Long?) {
        if (startRangeDate != null && endRangeDate != null) {
            _rangeUiState.value = RangeUIState(startRangeDate, endRangeDate)
        }
    }
}

data class RangeUIState(
    var startRangeDate: Long = 0,
    var endRangeDate: Long = 0,
)