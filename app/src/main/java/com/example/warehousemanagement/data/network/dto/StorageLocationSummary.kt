package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class StorageLocationSummary(
    @SerialName("storageLocationId") val storageLocationId: String,
    @SerialName("storageLocationName") val storageLocationName: String,
    @SerialName("totalQuantity") val totalQuantity: Int
)