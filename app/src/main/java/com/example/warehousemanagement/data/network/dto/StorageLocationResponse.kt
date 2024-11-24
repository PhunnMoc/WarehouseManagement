package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StorageLocationResponse(
    @SerialName("_id") val idStorageLocation: String?,
    @SerialName("storageLocationImage") val storageLocationImage: String?,
    @SerialName("storageLocationName") val storageLocationName: String?,
)