package com.example.warehousemanagement.domain.model

data class Supplier(
    val idSupplier: String, // Định danh nhà cung cấp
    val name: String, // Tên nhà cung cấp
    val address: Address // Địa chỉ nhà cung cấp
)