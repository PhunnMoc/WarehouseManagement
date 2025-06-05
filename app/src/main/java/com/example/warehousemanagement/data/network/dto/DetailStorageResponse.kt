package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class DetailStorageResponse(
    @SerialName("storageLocationId") val storageLocationId: String?,
    @SerialName("storageName") val storageName: String?,
    @SerialName("storageImage") val storageImage: String?,
    @SerialName("listProduct") val listProduct: List<ProductResponse>?,
)