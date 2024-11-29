package com.example.warehousemanagement.data.network.dto

import kotlinx.serialization.SerialName

data class UserResponse(
    @SerialName("_id") val _id: String?,              // Định danh người dùng
    @SerialName("username") val username: String?,           // Tên người dùng
    @SerialName("passwordHash") val passwordHash: String?,       // Mật khẩu đã băm (hashed password)
    @SerialName("information") val information: InformationResponse?
)