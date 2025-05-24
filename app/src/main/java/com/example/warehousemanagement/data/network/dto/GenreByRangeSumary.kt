package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class GenreByRangeSummaryResponse(
    @SerialName("genreName") val genreName: String,
    @SerialName("total") val total: Int,
    @SerialName("genreId") val genreId: String,
)