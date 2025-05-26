package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class TotalRevenueByYearResponse(
    @SerialName("totalRevenue")  val totalRevenue: Double,
    @SerialName("year") val year: Int
)