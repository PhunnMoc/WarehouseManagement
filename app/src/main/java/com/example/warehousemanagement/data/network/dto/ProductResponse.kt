package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("description") val description: String?,
    @SerialName("genre") val genre: GenreResponse?,
    @SerialName("id") val idProduct: String?,
    @SerialName("image") val image: String?,
    @SerialName("importPrice") val importPrice: Int?,
    @SerialName("inStock") val inStock: Boolean?,
    @SerialName("lastUpdated") val lastUpdated: String?,
    @SerialName("productName") val productName: String?,
    @SerialName("quantity") val quantity: Int?,
    @SerialName("sellingPrice") val sellingPrice: Int?,
    @SerialName("storageLocation") val storageLocation: StorageLocationResponse?,
    @SerialName("supplier") val supplier: SupplierResponse?,
)