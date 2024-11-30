package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Information
import com.example.warehousemanagement.domain.model.Notification
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.model.User

interface WareHouseRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(idProduct: String): Product
    suspend fun searchProductsByName(
        nameString: String
    ): List<Product>

    suspend fun sortProducts(
        sortBy: String,
        order: String,
    ): List<Product>

    suspend fun getAllGenre(): List<Genre>
    suspend fun addNewGenre(genre: Genre)

    suspend fun getSearchedGenresDetails(query: String): List<Genre>

    suspend fun getAllStoLocDetails(): List<StorageLocation>

    suspend fun getSearchedStorageLocationByName(query: String): List<StorageLocation>

    suspend fun getPendingImportPackages(): List<ImportPackages>
    suspend fun getPendingExportPackages(): List<ExportPackages>
    suspend fun getDoneImportPackages(): List<ImportPackages>
    suspend fun getDoneExportPackages(): List<ExportPackages>
    suspend fun createImportPackage(importPackage: ImportPackages)//: ImportPackages

    suspend fun getAllSuppliers(): List<Supplier>

    suspend fun getSearchedSupplierByName(query: String): List<Supplier>
    suspend fun getSupplierById(idSupplier: String): Supplier
    suspend fun getAllSupplierDetails(): List<Supplier>

    suspend fun getAllCustomers(): List<Customer>
    suspend fun getCustomerById(idCustomer: String): Customer
    suspend fun getAllCustomerDetails(): List<Customer>
    suspend fun addNewSupplier(supplier: Supplier)//: String

    /// Login
    suspend fun login(username: String, password: String): Map<String, String>
    suspend fun getUserDetails(id: String): User
    suspend fun getAllNotificationDetails(): List<Notification>
    suspend fun addNewCustomer(customer: Customer)//: String
    suspend fun searchSuppliersByName(nameString: String): List<Supplier>
    suspend fun searchCustomersByName(nameString: String): List<Customer>

}