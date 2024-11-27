package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import kotlinx.coroutines.flow.Flow

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

    suspend fun getAllStoLocDetails(): List<StorageLocation>

    suspend fun getAllImportPackages(): List<ImportPackages>
}