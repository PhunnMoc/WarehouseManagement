package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.data.mapper.convertToResponse
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import javax.inject.Inject

class WareHouseRepositoryImpl @Inject constructor(
    private val retrofit: ApiWarehouse
) : WareHouseRepository {
    override suspend fun getAllProducts(): List<Product> {
        return retrofit.getAllProducts().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getProductById(idProduct: String): Product {
        return retrofit.getProductDetails(id = idProduct).body()?.convertToModel()!!  //TODO()
    }

    override suspend fun searchProductsByName(nameString: String): List<Product> {
        return retrofit.getSearchedProductsDetails(
            props = "productName",
            value = nameString,
        ).body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun sortProducts(sortBy: String, order: String): List<Product> {
        TODO() //return retrofit
    }

    override suspend fun getAllGenre(): List<Genre> {
        return retrofit.getAllGenres().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }
    override suspend fun searchGenresByName(nameString: String): List<Genre> {
        return retrofit.getSearchesGenresDetails(
            props = "genreName", // Adjust the query parameter based on your API
            value = nameString
        ).body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }
    override suspend fun getAllStoLocDetails(): List<StorageLocation> {
        return retrofit.getAllStoLocDetails().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getAllSuppliers(): List<Supplier> {
        return retrofit.getAllSupplier().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getSupplierById(idSupplier: String): Supplier {
        return retrofit.getSupplierDetails(id = idSupplier).body()?.convertToModel()!!  //TODO()
    }

    override suspend fun getAllSupplierDetails(): List<Supplier> {
        TODO("Not yet implemented")
    }
    override suspend fun searchSuppliersByName(nameString: String): List<Supplier> {
        return retrofit.getSearchedSuppliersDetails(
            props = "supplierName", // Adjust the query parameter based on your API
            value = nameString
        ).body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getAllCustomers(): List<Customer> {
        return retrofit.getAllCustomer().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getCustomerById(idCustomer: String): Customer {
        return retrofit.getCustomerDetails(id = idCustomer).body()?.convertToModel()!!
    }

    override suspend fun getAllCustomerDetails(): List<Customer> {
        TODO("Not yet implemented")
    }
    override suspend fun searchCustomersByName(nameString: String): List<Customer> {
        return retrofit.getSearchedCustomerDetails(
            props = "customerName", // Adjust the query parameter based on your API
            value = nameString
        ).body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }
    override suspend fun getPendingImportPackages(): List<ImportPackages> {
        return retrofit.getPendingImportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun getDoneImportPackages(): List<ImportPackages> {
        return retrofit.getDoneImportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun addNewSupplier(supplier: Supplier) {
        return retrofit.addNewSupplier(supplier = supplier.convertToResponse())// ?: "Can't add"
    }
    override suspend fun addNewCustomer(customer: Customer) {
        return retrofit.addNewCustomer(customer = customer.convertToResponse())// ?: "Can't add"
    }

    override suspend fun addNewGenre(genre: Genre) {
        return retrofit.addNewGenre(genre = genre.convertToResponse())// ?: "Can't add"
    }
}
