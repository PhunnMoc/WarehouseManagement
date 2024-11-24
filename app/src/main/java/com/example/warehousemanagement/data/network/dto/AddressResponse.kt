package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    val city: String?,
    val district: String?,
    val idAddress: String?,
    val phone: String?,
    val postalCode: String?,
    val street: String?,
)