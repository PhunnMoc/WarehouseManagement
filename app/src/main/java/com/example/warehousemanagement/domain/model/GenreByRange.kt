package com.example.warehousemanagement.domain.model

import kotlinx.serialization.SerialName

data class GenreByRangeSummary(
    val genreName: String,
    val totalImported: Int,
    val genreId: String,
)