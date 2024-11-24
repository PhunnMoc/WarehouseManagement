package com.example.warehousemanagement.domain.model

import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse

data class Product(
    val description: String,
    val genre: Genre,
    val idProduct: String,
    val image: String,
    val importPrice: Int,
    val inStock: Boolean,
    val lastUpdated: String,
    val productName: String,
    val quantity: Int,
    val sellingPrice: Int,
    val storageLocation: StorageLocation,
    val supplier: Supplier,
)