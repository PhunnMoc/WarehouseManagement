package com.example.warehousemanagement.domain.model

data class Customer(
    val idCustomer: String,
    val email:String,
    val customerName: String,
    val address: Address,
)