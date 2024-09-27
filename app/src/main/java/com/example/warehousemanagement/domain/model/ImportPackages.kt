package com.example.warehousemanagement.domain.model

import java.util.Date

data class ImportPackages(
    val id: String,                // Định danh gói nhập
    val packageName: String,        // Tên gói nhập
    val importDate: Date,           // Ngày nhập hàng
    val supplier: Supplier,         // Thông tin nhà cung cấp
    val idReceivedBy: String,       // Người nhận hàng
    val status: Status= Status.PENDING,  // Tình trạng gói nhập, default is PENDING
    val note: String? = null        // Ghi chú, optional
)
