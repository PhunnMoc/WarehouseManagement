package com.example.warehousemanagement.domain.model

import com.example.warehousemanagement.data.network.dto.ReceiverResponse

data class ImportPackages(
//    val idImportPackages: String,
//    val packageName: String,
//    val importDate: Date,
//    val listProduct: List<Product>,
//    val supplier: SupplierResponse,
//    val statusDone: StatusPackage = StatusPackage.PENDING,
//    val note: String,
//    val statusDone: Boolean,
//    val receiver: ReceiverResponse,
    val id: String,
    val importDate: String,
    val listProducts: List<Product>,
    val note: String,
    val packageName: String,
    val receiver: ReceiverResponse,
    val statusDone: String,
)
