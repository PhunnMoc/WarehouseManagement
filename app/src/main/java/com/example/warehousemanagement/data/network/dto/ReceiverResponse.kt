package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.Serializable

data class ReceiverResponse(
    val _id: String,
    val information: InformationResponse?,
    val passwordHash: String?,
    val username: String?,
)