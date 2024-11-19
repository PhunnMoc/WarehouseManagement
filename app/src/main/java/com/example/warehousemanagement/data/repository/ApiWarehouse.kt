package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.network.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiWarehouse {
    @GET("/product")
    suspend fun getAllProducts(): Response<List<ProductResponse>>
}