package com.example.warehousemanagement.domain.model

import java.util.Date

data class ImportPackages(
    val idImportPackages: String,    // id gói nhập
    val packageName: String,        // Tên gói nhập
    val importDate: Date,           // Ngày nhập hàng
    val idProduct:String,
    val quantity:Int,
    val idSupplier: String,         // Thông tin nhà cung cấp
    val idReceivedBy: String,       // Người nhận hàng
    val status: StatusPackage= StatusPackage.PENDING,  // Tình trạng gói nhập, default is PENDING
    val totalAmount:Double,
    val note: String? = null        // Ghi chú, optional
)
