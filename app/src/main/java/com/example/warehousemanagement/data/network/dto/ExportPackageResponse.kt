package com.example.warehousemanagement.data.network.dto

import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Product

data class  ExportPackageResponse(
    val customer: CustomerResponse?,
    val deliveryMethod: String?,
    val exportDate: String?,
    val id: String?,
    val listProducts: List<ProductResponse>?,
    val note: String?,
    val packageName: String?,
    val sender: ReceiverResponse?,
    val statusDone: String?,
)
