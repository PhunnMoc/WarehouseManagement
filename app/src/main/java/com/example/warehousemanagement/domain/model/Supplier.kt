package com.example.warehousemanagement.domain.model

data class Supplier(
    val idSupplier: String,
    val name: String,
    val email: String,
    val address: Address,
    val ratings: Int?
)