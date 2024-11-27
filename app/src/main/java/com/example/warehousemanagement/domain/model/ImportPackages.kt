package com.example.warehousemanagement.domain.model

import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.ReceiverResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import java.util.Date

data class ImportPackages(
//    val idImportPackages: String,
//    val packageName: String,
//    val importDate: Date,
//    val listProduct: List<Product>,
//    val supplier: SupplierResponse,
//    val status: StatusPackage = StatusPackage.PENDING,
//    val note: String,
//    val statusDone: Boolean,
//    val receiver: ReceiverResponse,
    val idImportPackages: String,
    val importDate: String,
    val listProducts: List<Product>,
    val note: String,
    val packageName: String,
    val receiver: ReceiverResponse,
    val statusDone: Boolean,
    val supplier: Supplier,
    val status: StatusPackage = StatusPackage.PENDING,
    )
