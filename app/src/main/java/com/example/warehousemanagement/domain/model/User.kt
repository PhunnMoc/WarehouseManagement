package com.example.warehousemanagement.domain.model

data class User(
    val idUser: String,                // Định danh người dùng
    val username: String,           // Tên người dùng
    val information: Information?
)