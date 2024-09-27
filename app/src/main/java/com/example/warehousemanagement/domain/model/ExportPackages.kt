package com.example.warehousemanagement.domain.model

import java.util.Date

data class ExportPackages(
    val id: String,
    val packageName: String,
    val exportDate: Date,
    val customer: Customer,
    val shipTo: String,
    val status:Status = Status.PENDING,
    val deliveryMethod: String,
    val note: String? = null
)