package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class ProfitByYearResponse(
    @SerialName("months") val months: List<OverviewReportByMonth>,
    @SerialName("year") val year: Int
)

//fun List<OverviewReportByMonth>.getListCost(): List<Double> = this.map { it.cost }
//fun List<OverviewReportByMonth>.getListRevenue(): List<Double> = this.map { it.revenue }