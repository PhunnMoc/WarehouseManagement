package com.example.warehousemanagement.domain.model

data class Information(
    val idInformation: String,
    val firstName: String,          // Tên
    val lastName: String,           // Họ
    val email: String,               // Địa chỉ email
    val role: Role,                 // Thông tin vai trò
    val picture: String? = null     // URL hình ảnh (optional)
)