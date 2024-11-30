package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.network.dto.CustomerResponse
import com.example.warehousemanagement.data.network.dto.ExportPackageResponse
import com.example.warehousemanagement.data.network.dto.GenreResponse
import com.example.warehousemanagement.data.network.dto.ImportPackageResponseItem
import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import com.example.warehousemanagement.data.network.dto.UserResponse
import com.example.warehousemanagement.domain.model.Notification
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiWarehouse {
    @GET("/product")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @GET("/product/{id}")
    suspend fun getProductDetails(@Path("id") id: String): Response<ProductResponse>

    @GET("/product/search")
    suspend fun getSearchedProductsDetails(
        @Query("props") props: String,
        @Query("value") value: String
    ): Response<List<ProductResponse>>

    ///
    @GET("/product/sort")
    suspend fun getSortedProductDetails(
        @Query("props") props: String,
        @Query("order") order: String
    ): Response<List<ProductResponse>>


    @GET("/genre")
    suspend fun getAllGenres(): Response<List<GenreResponse>>

    @GET("/genre/search")
    suspend fun getSearchedGenresDetails(
        @Query("value") value: String
    ): Response<List<GenreResponse>>

    @GET("/storage-location")
    suspend fun getAllStoLocDetails(): Response<List<StorageLocationResponse>>

    @GET("/storage-location/search")
    suspend fun getSearchedStorageLocationByName(
        @Query("value") value: String
    ): Response<List<StorageLocationResponse>>


    @GET("/supplier")
    suspend fun getAllSupplier(): Response<List<SupplierResponse>>

    @GET("/supplier/search")
    suspend fun getSearchedSupplierByName(
        @Query("value") value: String
    ): Response<List<SupplierResponse>>

    @POST("/supplier") // Adjust the endpoint based on your backend configuration
    suspend fun addNewSupplier(
        @Body supplier: SupplierResponse
    )

    @GET("/import-packages/pending")
    suspend fun getPendingImportPackages(): Response<List<ImportPackageResponseItem>>

    @GET("/export-packages/pending")
    suspend fun getPendingExportPackages(): Response<List<ExportPackageResponse>>

    @GET("/import-packages/done")
    suspend fun getDoneImportPackages(): Response<List<ImportPackageResponseItem>>

    @GET("/export-packages/done")
    suspend fun getAllDoneExportPackages(): Response<List<ExportPackageResponse>>

    @POST("/import-packages") // Adjust the endpoint path to match your API
    suspend fun createImportPackage(
        @Body importPackage: ImportPackageResponseItem
    )//: Response<ImportPackageResponseItem>

    @GET("/supplier/{id}")
    suspend fun getSupplierDetails(@Path("id") id: String): Response<SupplierResponse>
    @GET("/supplier/search")
    suspend fun getSearchedSuppliersDetails(
        @Query("props") props: String,
        @Query("value") value: String
    ): Response<List<SupplierResponse>>
    @GET("/customer")
    suspend fun getAllCustomer(): Response<List<CustomerResponse>>

    @GET("/customer/{id}")
    suspend fun getCustomerDetails(@Path("id") id: String): Response<CustomerResponse>
    @GET("/customer/search")
    suspend fun getSearchedCustomerDetails(
        @Query("props") props: String,
        @Query("value") value: String
    ): Response<List<CustomerResponse>>
    @POST("/customer") // Adjust the endpoint based on your backend configuration
    suspend fun addNewCustomer(
        @Body customer: CustomerResponse
    )
    @POST("/genre") // Adjust the endpoint based on your backend configuration
    suspend fun addNewGenre(
        @Body genre: GenreResponse
    )

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<Map<String, String>>

    @GET("/user/{id}")
    suspend fun getUserDetails(
        @Path("id") id: String
    ): Response<UserResponse>

    @GET("/notification")
    suspend fun getAllNotificationDetails(): Response<List<Notification>>

}