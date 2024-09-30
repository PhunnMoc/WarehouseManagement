package com.example.warehousemanagement.domain.model

import java.util.Date

data class Product(
    val idProduct: String, // Định danh sản phẩm
    val productName: String, // Tên sản phẩm
    val genreId: String, // Loại sản phẩm
    val stockQuantity: Int, // Số lượng sản phẩm
    val description: String, // Mô tả sản phẩm
    val importPrice:Double,
    val sellingPrice:Double,
    val supplierId: String, // Thông tin nhà cung cấp
    val isInStock: Boolean, // Tình trạng hàng hóa
    val barcode: String, // Mã vạch
    val storageLocationId: String, // Khu vực
    val lastUpdated: Date, // Ngày cập nhật cuối
    val image:String
)