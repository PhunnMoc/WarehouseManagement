package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
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

    suspend fun getAllSuppliers(): List<Supplier>
    suspend fun getSupplierById(idSupplier: String): Supplier
    suspend fun getAllSupplierDetails(): List<Supplier>

    suspend fun getAllCustomers(): List<Customer>
    suspend fun getCustomerById(idCustomer: String): Customer
    suspend fun getAllCustomerDetails(): List<Customer>

}