package com.example.warehousemanagement.domain.model

import java.util.Date

data class ExportPackages(
    val idExportPackages: String,
    val packageName: String,
    val listProduct: List<Product>,
    val exportDate: String,
    val customer: Customer,
    val status: String,//StatusPackage = StatusPackage.PENDING,
    val deliveryMethod: String,
    val note: String? = null,
    val sender: User,
)