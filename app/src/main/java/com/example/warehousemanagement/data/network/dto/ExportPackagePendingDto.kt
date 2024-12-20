package com.example.warehousemanagement.data.network.dto

data class ExportPackagePendingDto(
    val customerId: String,
    val deliveryMethod: String,
    val idSender: String,
    val listProducts: List<ExportProductDto>,
    val note: String,
    val packageName: String
)