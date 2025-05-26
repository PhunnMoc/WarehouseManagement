package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class TotalCostByYearResponse(
    @SerialName("totalCost") val totalCost: Double,
    @SerialName("year") val year: Int
)