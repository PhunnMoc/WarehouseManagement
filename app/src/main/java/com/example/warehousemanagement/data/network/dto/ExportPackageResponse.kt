package com.example.warehousemanagement.data.network.dto

data class ExportPackageResponse(
    val customer: CustomerResponse?,
    val deliveryMethod: String?,
    val exportDate: String?,
    val id: String?,
    val listProducts: List<ExportProductResponse>?,
    val note: String?,
    val packageName: String?,
    val sender: ReceiverResponse?,
    val statusDone: String?,
    val totalSellingPrice: String = "",
)
