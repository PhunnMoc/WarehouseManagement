package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.Serializable
import java.util.Date


data class ImportPackageResponseItem(
    val id: String?,
    val importDate: String?,
    val listProducts: List<ProductResponse>?,
    val note: String?,
    val packageName: String?,
    val `receiver`: ReceiverResponse?,
    val statusDone: String?,
)