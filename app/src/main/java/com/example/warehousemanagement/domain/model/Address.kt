package com.example.warehousemanagement.domain.model

data class Address(
    val idAddress:String,
    val street: String, // Địa chỉ chi tiết
    val district: String, // Quận
    val city: String, // Thành phố
    val postalCode: String, // Mã bưu điện
    val phone: String // Số điện thoại
)