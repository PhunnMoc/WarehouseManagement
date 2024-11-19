package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Product

interface WareHouseRepository {
    suspend fun getAllProducts(): List<Product>
}