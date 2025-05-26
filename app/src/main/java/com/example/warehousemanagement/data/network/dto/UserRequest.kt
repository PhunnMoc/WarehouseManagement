package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class UserRequest(
    @SerialName("information") val information: InformationRequest,
    @SerialName("password") val password: String,
    @SerialName("username") val username: String
)