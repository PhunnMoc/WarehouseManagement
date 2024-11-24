package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
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

}