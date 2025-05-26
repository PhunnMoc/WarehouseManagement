package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class InformationRequest(
    @SerialName("email") val email: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("picture") val picture: String,
    @SerialName("role") val role: String
)