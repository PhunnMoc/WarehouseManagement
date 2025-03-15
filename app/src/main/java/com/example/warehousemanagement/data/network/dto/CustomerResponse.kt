package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CustomerResponse(
    @SerialName("id") val id: String?,
    @SerialName("address") val address: AddressResponse?,
    @SerialName("email") val email: String?,
    @SerialName("customerName") val customerName: String?,
)