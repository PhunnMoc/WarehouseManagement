package com.example.warehousemanagement.ui.feature.report.ui

import com.example.warehousemanagement.data.network.dto.MonthlyCostResponse
import com.example.warehousemanagement.data.network.dto.MonthlyRevenueResponse

data class MonthlyUiState(
    val month: Int,
    val total: Float,
)

fun MonthlyRevenueResponse.convertToUiState(): MonthlyUiState {
    return MonthlyUiState(
        month = this.month,
        total = this.totalRevenue,
    )
}

fun MonthlyCostResponse.convertToUiState(): MonthlyUiState {
    return MonthlyUiState(
        month = this.month,
        total = this.totalCost,
    )
}