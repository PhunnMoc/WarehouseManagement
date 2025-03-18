package com.example.warehousemanagement.domain.model

data class Product(
    val id: String,
    val description: String,
    val genre: Genre?,
    val image: String,
    val importPrice: Int,
    val inStock: Boolean,
    val lastUpdated: String,
    val productName: String,
    val quantity: Int,
    val sellingPrice: Int,
    val storageLocation: StorageLocation?,
    val supplier: Supplier?,
)