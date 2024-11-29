package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier

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

    suspend fun getSearchedGenresDetails(query: String): List<Genre>

    suspend fun getAllStoLocDetails(): List<StorageLocation>

    suspend fun getSearchedStorageLocationByName(query: String): List<StorageLocation>

    suspend fun getPendingImportPackages(): List<ImportPackages>
    suspend fun getDoneImportPackages(): List<ImportPackages>
    suspend fun createImportPackage(importPackage: ImportPackages)//: ImportPackages

    suspend fun getAllSuppliers(): List<Supplier>

    suspend fun getSearchedSupplierByName(query: String): List<Supplier>
    suspend fun getSupplierById(idSupplier: String): Supplier
    suspend fun getAllSupplierDetails(): List<Supplier>

    suspend fun getAllCustomers(): List<Customer>
    suspend fun getCustomerById(idCustomer: String): Customer
    suspend fun getAllCustomerDetails(): List<Customer>
    suspend fun addNewSupplier(supplier: Supplier)//: String


}