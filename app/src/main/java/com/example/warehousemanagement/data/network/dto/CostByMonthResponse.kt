package com.example.warehousemanagement.data.network.dto

data class CostByMonthResponse(
    val packageId: String,
    val packageName: String,
    val importDate: String,
    val  totalImportPrice: Float,
)