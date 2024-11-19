package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.network.dto.convertToModel
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.repository.WareHouseRepository
import javax.inject.Inject

class WareHouseRepositoryImpl @Inject constructor(
    private val retrofit: ApiWarehouse
) : WareHouseRepository {
    override suspend fun getAllProducts(): List<Product> {
        return retrofit.getAllProducts().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

}