package com.example.warehousemanagement.ui.feature.report.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.OverviewReportByMonth
import com.example.warehousemanagement.data.network.dto.ProfitByYearResponse
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
) : ViewModel() {
    private val _year = MutableStateFlow(2025)
    val year: StateFlow<Int> = _year.asStateFlow()

    private val _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState.Loading)
    val uiState: StateFlow<OverviewUiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun updateYear(newYear: Int) {
        _year.value = newYear
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _uiState.value = OverviewUiState.Loading
            try {
                val revenue = wareHouseRepository.getTotalRevenueByYear(_year.value)
                val cost = wareHouseRepository.getTotalCostByYear(_year.value)
                val listProfitByYear =
                    async { wareHouseRepository.getStaticProfitYear(_year.value) }
                _uiState.value = OverviewUiState.Success(
                    revenue = revenue.totalRevenue,
                    cost = cost.totalCost,
                    listProfitByYear = listProfitByYear.await().months,
                )
            } catch (e: Exception) {
                _uiState.value = OverviewUiState.Error
            }
        }
    }
}

sealed class OverviewUiState {
    data class Success(
        val revenue: Double = 0.0,
        val cost: Double = 0.0,
        val listProfitByYear: List<OverviewReportByMonth> = listOf(),
    ) : OverviewUiState()

    data object Loading : OverviewUiState()
    data object Error : OverviewUiState()
}
