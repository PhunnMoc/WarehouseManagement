package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class AddressResponse(
    @SerialName("city") val city: String?,
    @SerialName("district")val district: String?,
    @SerialName("phone")val phone: String?,
    @SerialName("postalCode")val postalCode: String?,
    @SerialName("street")val street: String?,
)