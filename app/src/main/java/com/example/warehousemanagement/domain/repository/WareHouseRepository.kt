package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.data.network.dto.CostByMonthResponse
import com.example.warehousemanagement.data.network.dto.DetailStorageResponse
import com.example.warehousemanagement.data.network.dto.ExportPackagePendingDto
import com.example.warehousemanagement.data.network.dto.GenreByRangeSummaryResponse
import com.example.warehousemanagement.data.network.dto.MonthlyCostResponse
import com.example.warehousemanagement.data.network.dto.MonthlyRevenueResponse
import com.example.warehousemanagement.data.network.dto.ProfitByYearResponse
import com.example.warehousemanagement.data.network.dto.RevenueByMonthResponse
import com.example.warehousemanagement.data.network.dto.StorageLocationSummary
import com.example.warehousemanagement.data.network.dto.TotalCostByYearResponse
import com.example.warehousemanagement.data.network.dto.TotalRevenueByYearResponse
import com.example.warehousemanagement.data.network.dto.UserRequest
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
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

    suspend fun getFilteredProductsDetails(
        filters: Map<String, String>
    ): List<Product>

    suspend fun getAllGenre(): List<Genre>
    suspend fun addNewGenre(genre: Genre)

    suspend fun getSearchedGenresDetails(query: String): List<Genre>

    suspend fun getAllStoLocDetails(): List<StorageLocation>

    suspend fun getSearchedStorageLocationByName(query: String): List<StorageLocation>
    suspend fun getProductsBelongStorage(id: String): DetailStorageResponse

    suspend fun getPendingImportPackages(): List<ImportPackages>
    suspend fun getPendingImportPackageById(id: String): ImportPackages

    suspend fun getDoneImportPackageById(id: String): ImportPackages
    suspend fun getImportPackageById(id: String): ImportPackages
    suspend fun updateImportPackage(
        id: String,
        status: String,
    )

    suspend fun updateProductsInImportPackage(
        id: String,
        storageLocationIds: Map<String, String>
    )

    suspend fun updatePendingImportPackage(
        id: String,
        updatedImportPackage: ImportPackages,
    )

    suspend fun updatePendingExportPackage(
        id: String,
        updatedExportPackage: ExportPackages,
    )

    suspend fun getPendingExportPackages(): List<ExportPackages>
    suspend fun addPendingExportPackages(pendingExportPackage: ExportPackagePendingDto)
    suspend fun getDoneImportPackages(): List<ImportPackages>

    suspend fun getExportPackageById(id: String): ExportPackages
    suspend fun approveExportPackage(id: String)
    suspend fun getDoneExportPackages(): List<ExportPackages>
    suspend fun createImportPackage(importPackage: ImportPackages)//: ImportPackages

    suspend fun getAllSuppliers(): List<Supplier>

    suspend fun getSearchedSupplierByName(query: String): List<Supplier>
    suspend fun getSupplierById(idSupplier: String): Supplier
    suspend fun updateSupplier(id: String, supplier: Supplier)
    suspend fun getAllSupplierDetails(): List<Supplier>

    suspend fun getAllCustomers(): List<Customer>
    suspend fun getCustomerById(idCustomer: String): Customer?
    suspend fun getAllCustomerDetails(): List<Customer>
    suspend fun addNewSupplier(supplier: Supplier)//: String

    /// Login
    suspend fun login(username: String, password: String): Map<String, String>
    suspend fun getUserDetails(id: String): User
    suspend fun updateUserDetail(id: String, user: User)
    suspend fun addNewUser(user: UserRequest)
    suspend fun getAllUserDetails(): List<User>
    suspend fun getAllNotificationDetails(): List<Notification>
    suspend fun addNewCustomer(customer: Customer)//: String
    suspend fun updateNewCustomer(
        id: String,
        customer: Customer
    )//: String

    suspend fun searchSuppliersByName(nameString: String): List<Supplier>
    suspend fun searchCustomersByName(nameString: String): List<Customer>

    suspend fun getStockSummaryByLocation(): List<StorageLocationSummary>
    suspend fun getStaticProfitYear(year: Int): ProfitByYearResponse
    suspend fun getTotalRevenueByYear(year: Int): TotalRevenueByYearResponse
    suspend fun getTotalCostByYear(year: Int): TotalCostByYearResponse
    suspend fun getGenreByRangeSummaryImport(
        startDate: Long,
        endDate: Long,
        limit: Int,
    ): List<GenreByRangeSummaryResponse>

    suspend fun getGenreByRangeSummaryExport(
        startDate: Long,
        endDate: Long,
        limit: Int,
    ): List<GenreByRangeSummaryResponse>

    suspend fun getMonthlyRevenue(
        year: Int,
    ): List<MonthlyRevenueResponse>

    suspend fun getDetailMonthlyRevenue(
        year: Int,
        month: Int,
    ): List<RevenueByMonthResponse>

    suspend fun getDetailMonthlyCost(
        year: Int,
        month: Int,
    ): List<CostByMonthResponse>

    suspend fun getMonthlyCost(
        year: Int,
    ): List<MonthlyCostResponse>

    suspend fun getAnswerChatBox(
        question: String,
    ): String

    suspend fun syncDataChatBox(
    )
}