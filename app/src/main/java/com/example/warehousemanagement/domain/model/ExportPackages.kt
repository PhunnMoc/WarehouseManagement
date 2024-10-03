package com.example.warehousemanagement.domain.model

import java.util.Date

data class ExportPackages(
    val idExportPackages: String,
    val packageName: String,
    val listProduct: List<Product>,
    val totalAmount: Double,
    val exportDate: Date,
    val idCustomer: String,
    val shipTo: String,
    val status: StatusPackage = StatusPackage.PENDING,
    val deliveryMethod: String,
    val note: String? = null
)