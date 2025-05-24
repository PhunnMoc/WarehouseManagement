package com.example.warehousemanagement.data.repository

import androidx.room.Delete
import com.example.warehousemanagement.data.network.dto.CostByMonthResponse
import com.example.warehousemanagement.data.network.dto.CustomerResponse
import com.example.warehousemanagement.data.network.dto.ExportPackagePendingDto
import com.example.warehousemanagement.data.network.dto.ExportPackageResponse
import com.example.warehousemanagement.data.network.dto.GenreByRangeSummaryResponse
import com.example.warehousemanagement.data.network.dto.GenreResponse
import com.example.warehousemanagement.data.network.dto.ImportPackageResponseItem
import com.example.warehousemanagement.data.network.dto.MonthlyCostResponse
import com.example.warehousemanagement.data.network.dto.MonthlyRevenueResponse
import com.example.warehousemanagement.data.network.dto.ProductResponse
import com.example.warehousemanagement.data.network.dto.RevenueByMonthResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationSummary
import com.example.warehousemanagement.data.network.dto.SupplierRequest
import com.example.warehousemanagement.data.network.dto.SupplierResponse
import com.example.warehousemanagement.data.network.dto.UserResponse
import com.example.warehousemanagement.domain.model.Notification
import com.example.warehousemanagement.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


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

    @GET("/product/filter")
    suspend fun getFilteredProductsDetails(
        @QueryMap filters: Map<String, String>
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
        @Body supplier: SupplierRequest
    )

    @GET("/import-packages/pending")
    suspend fun getPendingImportPackages(): Response<List<ImportPackageResponseItem>>

    @GET("/import-packages/pending/{id}")
    suspend fun getPendingImportPackageById(
        @Path("id") id: String
    ): Response<ImportPackageResponseItem>

    @GET("/import-packages/done/{id}")
    suspend fun getDoneImportPackageById(
        @Path("id") id: String
    ): Response<ImportPackageResponseItem>

    @GET("/import-packages/{id}")
    suspend fun getImportPackageById(
        @Path("id") id: String
    ): Response<ImportPackageResponseItem>

    @PUT("/import-packages/{id}")
    suspend fun updateImportPackage(
        @Path("id") id: String,
        @Query("status") status: String,
    )

    @PUT("/import-packages/{id}/update-products")
    suspend fun updateProductsLocationInImportPackage(
        @Path("id") id: String,
        @QueryMap(encoded = true) storageLocationIds: Map<String, String>
    )

    @PUT("/import-packages/pending/{id}/update")
    suspend fun updatePendingImportPackage(
        @Path("id") id: String,
        @Body updatedImportPackage: ImportPackageResponseItem,
    )

    @PUT("/export-packages/pending/{id}/update")
    suspend fun updatePendingExportPackage(
        @Path("id") id: String,
        @Body updatedExportPackage: ExportPackageResponse,
    )

    @GET("/export-packages/pending")
    suspend fun getPendingExportPackages(): Response<List<ExportPackageResponse>>

    @POST("/export-packages/pending")
    suspend fun addPendingExportPackages(
        @Body pendingExportPackage: ExportPackagePendingDto,
    )

    @GET("export-packages/{id}")
    suspend fun getExportPackageById(
        @Path("id") id: String
    ): Response<ExportPackageResponse>

    @PUT("export-packages/approve/{packageId}")
    suspend fun approveExportPackage(
        @Path("packageId") packageId: String,
    )

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

    @PUT("/supplier/{id}")
    suspend fun editSupplier(
        @Path("id") id: String,
        @Body supplier: SupplierResponse,
    )

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

    @PUT("/customer/{id}") // Adjust the endpoint based on your backend configuration
    suspend fun editCustomer(
        @Path("id") id: String,
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

    @GET("/user")
    suspend fun getAllUserDetails(): Response<List<UserResponse>>

    @PUT("/user/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body updated: User,
    )

    @POST("/user")
    suspend fun addNewUser(
        @Body updated: User,
    )

    @GET("/notification")
    suspend fun getAllNotificationDetails(): Response<List<Notification>>


    @GET("/statistic/in-stock")
    suspend fun getStockSummaryByLocation(): Response<List<StorageLocationSummary>>

    @GET("/statistic/top-genres-imported")
    suspend fun getTopGenreByRangeImport(
        @Query("startDate") startDate: Long,
        @Query("endDate") endDate: Long,
        @Query("limit") limit: Int,
    ): Response<List<GenreByRangeSummaryResponse>>

    @GET("/statistic/top-genres-exported")
    suspend fun getTopGenreByRangeExport(
        @Query("startDate") startDate: Long,
        @Query("endDate") endDate: Long,
        @Query("limit") limit: Int,
    ): Response<List<GenreByRangeSummaryResponse>>

    @GET("/statistic/monthly-revenue/{year}")
    suspend fun getMonthlyRevenue(
        @Path("year") year: Int,
    ): Response<List<MonthlyRevenueResponse>>

    @GET("/statistic/monthly-revenue/{year}/{month}")
    suspend fun getDetailMonthlyRevenue(
        @Path("year") year: Int,
        @Path("month") month: Int,
    ): Response<List<RevenueByMonthResponse>>

    @GET("/statistic/monthly-cost/{year}")
    suspend fun getMonthlyCost(
        @Path("year") year: Int,
    ): Response<List<MonthlyCostResponse>>

    @GET("/statistic/monthly-cost/{year}/{month}")
    suspend fun getDetailMonthlyCost(
        @Path("year") year: Int,
        @Path("month") month: Int,
    ): Response<List<CostByMonthResponse>>

    @GET("/chatbot/query")
    suspend fun getAnswerChatBox(@Query("question") question: String): Response<String>
}