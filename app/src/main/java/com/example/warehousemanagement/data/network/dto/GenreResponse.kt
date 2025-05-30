package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GenreResponse(
    @SerialName("_id") val _id: String?,
    @SerialName("genreName") val genreName: String?,
)