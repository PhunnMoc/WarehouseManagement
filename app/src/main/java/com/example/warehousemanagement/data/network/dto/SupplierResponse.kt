package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class SupplierResponse(
    @SerialName("_id") val _id: String?,
    @SerialName("addressResponse") val address: AddressResponse?,
    @SerialName("email") val email: String?,
    @SerialName("customerName") val name: String?,
    @SerialName("ratings") val ratings: Int?,
)

data class SupplierRequest(
    @SerialName("addressResponse") val address: AddressResponse?,
    @SerialName("email") val email: String?,
    @SerialName("customerName") val name: String?,
    @SerialName("ratings") val ratings: Int?,
)