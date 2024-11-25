package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import kotlinx.coroutines.flow.Flow

interface WareHouseRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(idProduct: String): Product
    fun searchProductsByName(
        nameString: String
    ): Flow<List<Product>>

    suspend fun sortProducts(
        sortBy: String,
        order: String = "asc",
    ): List<Product>

    suspend fun getAllGenre(): List<Genre>

    suspend fun getAllStoLocDetails(): List<StorageLocation>
}