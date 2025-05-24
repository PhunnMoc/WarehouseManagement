package com.example.warehousemanagement.ui.feature.report.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagement.data.network.dto.CostByMonthResponse
import com.example.warehousemanagement.data.network.dto.MonthlyCostResponse
import com.example.warehousemanagement.data.network.dto.MonthlyRevenueResponse
import com.example.warehousemanagement.data.network.dto.RevenueByMonthResponse
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RevenueViewModel @Inject constructor(
    private val wareHouseRepository: WareHouseRepository,
) : ViewModel() {
    val _revenueByYearUiState = MutableStateFlow<List<MonthlyRevenueResponse>>(listOf())

    val revenueByMonthUiState: StateFlow<List<MonthlyRevenueResponse>> =
        _revenueByYearUiState.asStateFlow()

    val _revenueDetailUiState = MutableStateFlow<List<RevenueByMonthResponse>>(listOf())

    val revenueDetailUiState: StateFlow<List<RevenueByMonthResponse>> =
        _revenueDetailUiState.asStateFlow()

    val _costByYearUiState = MutableStateFlow<List<MonthlyCostResponse>>(listOf())

    val costByMonthUiState: StateFlow<List<MonthlyCostResponse>> =
        _costByYearUiState.asStateFlow()

    val _costDetailUiState = MutableStateFlow<List<CostByMonthResponse>>(listOf())

    val costDetailUiState: StateFlow<List<CostByMonthResponse>> =
        _costDetailUiState.asStateFlow()



    fun getRevenueByYear(
        year: Int,
    ) {
        viewModelScope.launch {
            _revenueByYearUiState.value = wareHouseRepository.getMonthlyRevenue(year = year)
        }
        _revenueDetailUiState.value = listOf()
    }

    fun getCostByYear(
        year: Int,
    ) {
        viewModelScope.launch {
            _costByYearUiState.value = wareHouseRepository.getMonthlyCost(year = year)
        }
    }

    fun getRevenueByMonthImport(
        year: Int,
        month: Int,
    ) {
        viewModelScope.launch {
            _revenueDetailUiState.value = wareHouseRepository.getDetailMonthlyRevenue(
                year = year,
                month = month
            )
        }
    }
    fun getCostByMonthImport(
        year: Int,
        month: Int,
    ) {
        viewModelScope.launch {
            _costDetailUiState.value = wareHouseRepository.getDetailMonthlyCost(
                year = year,
                month = month
            )
        }
    }
}