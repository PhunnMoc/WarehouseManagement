package com.example.warehousemanagement.domain.model

import java.util.Date

data class Product(
    val id: String, // Định danh sản phẩm
    val productName: String, // Tên sản phẩm
    val genre: Genre, // Loại sản phẩm
    val quantity: Int, // Số lượng sản phẩm
    val description: String, // Mô tả sản phẩm
    val supplier: Supplier, // Thông tin nhà cung cấp
    val isInStock: Boolean, // Tình trạng hàng hóa
    val barcode: String, // Mã vạch
    val area: Area, // Khu vực
    val lastUpdated: Date // Ngày cập nhật cuối
)