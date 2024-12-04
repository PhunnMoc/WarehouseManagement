package com.example.warehousemanagement.data.network.dto

data class PendingProduct(
    val description: String,
    val genreId: GenreId,
    val id: String,
    val image: Any,
    val importPrice: Int,
    val lastUpdated: Any,
    val productName: String,
    val quantity: Int,
    val sellingPrice: Int,
    val supplierId: SupplierId
)