package com.example.warehousemanagement.domain.model

import java.util.Date

data class Product(
    val idProduct: String, // Định danh sản phẩm
    val productName: String, // Tên sản phẩm
    val genreId: String, // Loại sản phẩm
    val quantity: Int, // Số lượng sản phẩm
    val description: String, // Mô tả sản phẩm
    val importPrice: Double = 0.0,
    val sellingPrice: Double = 0.0,
    val supplierId: String = "Unknown", // Thông tin nhà cung cấp
    val isInStock: Boolean, // Tình trạng hàng hóa
    val barcode: String = "N/A", // Mã vạch
    val storageLocationId: String = "Default", // Khu vực
     val lastUpdated: Date = Date() , // Ngày cập nhật cuối
    val image: String? = null
)