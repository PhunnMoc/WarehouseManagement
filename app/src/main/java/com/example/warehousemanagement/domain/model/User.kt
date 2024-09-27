package com.example.warehousemanagement.domain.model

data class User(
    val id: String,                // Định danh người dùng
    val username: String,           // Tên người dùng
    val passwordHash: String,       // Mật khẩu đã băm (hashed password)
    val role: Role,                 // Thông tin vai trò
    val information: Information,   // Thông tin cá nhân
    val picture: String? = null     // URL hình ảnh (optional)
)