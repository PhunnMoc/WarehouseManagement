package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.Serializable

@Serializable

data class ImportPackageResponseItem(
    val id: String?,
    val importDate: String?,
    val listProducts: List<ProductResponse>?,
    val note: String?,
    val packageName: String?,
    val `receiver`: ReceiverResponse?,
    val statusDone: Boolean?,
    val supplier: SupplierResponse?,
)