package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupplierResponse(
    @SerialName("_id") val idSupplier: String?,
    @SerialName("addressResponse") val address: AddressResponse?,
    @SerialName("email") val email: String?,
    @SerialName("name") val name: String?,
    @SerialName("ratings") val ratings: Int?,
)