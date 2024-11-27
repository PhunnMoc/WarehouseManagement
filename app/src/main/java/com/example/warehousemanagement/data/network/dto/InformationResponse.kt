package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class InformationResponse(
    val email: String?,
    val firstName: String?,
    val idInformation: String?,
    val lastName: String?,
    val picture: String?,
    val role: String?,
)