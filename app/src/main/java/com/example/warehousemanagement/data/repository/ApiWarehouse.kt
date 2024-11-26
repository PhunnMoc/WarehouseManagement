package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.network.dto.CustomerResponse
import com.example.warehousemanagement.data.network.dto.GenreResponse
import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiWarehouse {
    @GET("/product")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @GET("/product/{id}")
    suspend fun getProductDetails(@Path("id") id: String): Response<ProductResponse>

    @GET("product/search")
    fun getSearchedProductsDetails(
        @Query("props") props: String,
        @Query("value") value: String
    ): Flow<List<ProductResponse>>

    ///
    @GET("/product/sort")
    suspend fun getSortedProductDetails(
        @Query("props") props: String,
        @Query("order") order: String
    ): Response<List<ProductResponse>>

    @GET("/genre")
    suspend fun getAllGenres(): Response<List<GenreResponse>>

    @GET("/storage-location")
    suspend fun getAllStoLocDetails(): Response<List<StorageLocationResponse>>
    @GET("/supplier")
    suspend fun getAllSupplier(): Response<List<SupplierResponse>>

    @GET("/supplier/{id}")
    suspend fun getSupplierDetails(@Path("id") id: String): Response<SupplierResponse>
    @GET("/customer")
    suspend fun getAllCustomer(): Response<List<CustomerResponse>>

    @GET("/customer/{id}")
    suspend fun getCustomerDetails(@Path("id") id: String): Response<CustomerResponse>
}