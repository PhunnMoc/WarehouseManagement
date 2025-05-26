package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class OverviewReportByMonth(
    @SerialName("cost") val cost: Double,
    @SerialName("profit") val profit: Boolean,
    @SerialName("revenue") val revenue: Double
)