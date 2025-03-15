package com.example.warehousemanagement.domain.model

import java.util.Date

data class ExportPackages(
    val idExportPackages: String,
    val packageName: String,
    val listProduct:  Map<Product,Int>,
    val exportDate: String,
    val customer: Customer,
    val status: String,//StatusPackage = StatusPackage.PENDING,
    val note: String? = null,
    val sender: User,
)