package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("_id") val idGenre: String?,
    @SerialName("genreName") val genreName: String?,
)