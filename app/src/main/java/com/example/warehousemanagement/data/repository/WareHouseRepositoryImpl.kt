package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
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

    override fun searchProductsByName(nameString: String): Flow<List<Product>> {
        return retrofit.getSearchedProductsDetails(
            props = "productName",
            value = nameString,
        ).mapNotNull { products -> products.mapNotNull { product -> product.convertToModel() } }
    }

    override suspend fun sortProducts(sortBy: String, order: String): List<Product> {
        TODO() //return retrofit
    }

    override suspend fun getAllGenre(): List<Genre> {
        return retrofit.getAllGenres().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getAllStoLocDetails(): List<StorageLocation> {
        return retrofit.getAllStoLocDetails().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

}