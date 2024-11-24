package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product

interface WareHouseRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(idProduct: String): Product
    suspend fun searchProductsByName(
        nameString: String
    ): List<Product>
    suspend fun sortProducts(
        sortBy: String,
        order: String = "asc",
    ): List<Product>

    suspend fun getAllGenre(): List<Genre>
}