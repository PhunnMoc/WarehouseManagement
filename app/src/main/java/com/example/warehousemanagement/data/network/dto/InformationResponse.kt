package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class InformationResponse(
    @SerialName("email") val email: String?,
    @SerialName("firstName")val firstName: String?,
    @SerialName("idInformation") val idInformation: String?,
    @SerialName("lastName")val lastName: String?,
    @SerialName("picture")val picture: String?,
    @SerialName("role")val role: String?,
)