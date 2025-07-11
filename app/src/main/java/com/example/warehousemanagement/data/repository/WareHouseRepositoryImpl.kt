package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.data.mapper.convertToRequest
import com.example.warehousemanagement.data.mapper.convertToResponse
import com.example.warehousemanagement.data.network.dto.CostByMonthResponse
import com.example.warehousemanagement.data.network.dto.DetailStorageResponse
import com.example.warehousemanagement.data.network.dto.ExportPackagePendingDto
import com.example.warehousemanagement.data.network.dto.GenreByRangeSummaryResponse
import com.example.warehousemanagement.data.network.dto.MessageChatBoxResponse
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
        return retrofit.getSortedProductDetails(
            props = sortBy,
            order = order,
        ).body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getFilteredProductsDetails(filters: Map<String, String>): List<Product> {
        return retrofit.getFilteredProductsDetails(filters).body()
            ?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getAllGenre(): List<Genre> {
        return retrofit.getAllGenres().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getSearchedGenresDetails(query: String): List<Genre> {
        return retrofit.getSearchedGenresDetails(value = query).body()
            ?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getAllStoLocDetails(): List<StorageLocation> {
        return retrofit.getAllStoLocDetails().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getSearchedStorageLocationByName(query: String): List<StorageLocation> {
        return retrofit.getSearchedStorageLocationByName(value = query).body()
            ?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getProductsBelongStorage(id: String): DetailStorageResponse {
        return retrofit.getProductsBelongStorage(id = id).body()!!
    }

    override suspend fun getAllSuppliers(): List<Supplier> {
        return retrofit.getAllSupplier().body()?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getSearchedSupplierByName(query: String): List<Supplier> {
        return retrofit.getSearchedSupplierByName(value = query).body()
            ?.mapNotNull { it.convertToModel() } ?: listOf()
    }

    override suspend fun getSupplierById(idSupplier: String): Supplier {
        return retrofit.getSupplierDetails(id = idSupplier).body()?.convertToModel()!!  //TODO()
    }

    override suspend fun updateSupplier(id: String, supplier: Supplier) {
        return retrofit.editSupplier(id = id, supplier = supplier.convertToResponse())
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

    override suspend fun getCustomerById(idCustomer: String): Customer? {
        return retrofit.getCustomerDetails(id = idCustomer).body()?.convertToModel()
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

    override suspend fun getStockSummaryByLocation(): List<StorageLocationSummary> {
        return retrofit.getStockSummaryByLocation().body() ?: listOf()
    }

    override suspend fun getStaticProfitYear(year: Int): ProfitByYearResponse {
        return retrofit.getStaticProfitYear(year = year).body()!!
    }

    override suspend fun getTotalRevenueByYear(year: Int): TotalRevenueByYearResponse {
        return retrofit.getTotalRevenueByYear(year = year).body() ?: TotalRevenueByYearResponse(
            0.0,
            0
        )
    }

    override suspend fun getTotalCostByYear(year: Int): TotalCostByYearResponse {
        return retrofit.getTotalCostByYear(year = year).body() ?: TotalCostByYearResponse(
            0.0,
            0
        )

    }

    override suspend fun getGenreByRangeSummaryImport(
        startDate: Long,
        endDate: Long,
        limit: Int,
    ): List<GenreByRangeSummaryResponse> {
        return retrofit.getTopGenreByRangeImport(
            startDate = startDate,
            endDate = endDate,
            limit = limit,
        ).body() ?: listOf()
    }

    override suspend fun getGenreByRangeSummaryExport(
        startDate: Long,
        endDate: Long,
        limit: Int
    ): List<GenreByRangeSummaryResponse> {
        return retrofit.getTopGenreByRangeExport(
            startDate = startDate,
            endDate = endDate,
            limit = limit,
        ).body() ?: listOf()
    }

    override suspend fun getMonthlyRevenue(
        year: Int,
    ): List<MonthlyRevenueResponse> {
        return retrofit.getMonthlyRevenue(
            year = year,
        ).body() ?: listOf()
    }

    override suspend fun getDetailMonthlyRevenue(
        year: Int,
        month: Int,
    ): List<RevenueByMonthResponse> {
        return retrofit.getDetailMonthlyRevenue(
            year = year,
            month = month,
        ).body() ?: listOf()
    }

    override suspend fun getMonthlyCost(year: Int): List<MonthlyCostResponse> {
        return retrofit.getMonthlyCost(
            year = year,
        ).body() ?: listOf()
    }

    override suspend fun getDetailMonthlyCost(
        year: Int,
        month: Int,
    ): List<CostByMonthResponse> {
        return retrofit.getDetailMonthlyCost(
            year = year,
            month = month,
        ).body() ?: listOf()
    }

    override suspend fun getPendingImportPackages(): List<ImportPackages> {
        return retrofit.getPendingImportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun getPendingImportPackageById(id: String): ImportPackages {
        return retrofit.getPendingImportPackageById(id = id).body()?.convertToModel()!!
    }

    override suspend fun getDoneImportPackageById(id: String): ImportPackages {
        return retrofit.getDoneImportPackageById(id = id).body()?.convertToModel()!!
    }

    override suspend fun getImportPackageById(id: String): ImportPackages {
        return retrofit.getImportPackageById(id = id).body()?.convertToModel()!!
    }

    override suspend fun updateImportPackage(
        id: String, status: String
    ) {
        return retrofit.updateImportPackage(
            id = id, status = status
        )
    }

    override suspend fun updateProductsInImportPackage(
        id: String,
        storageLocationIds: Map<String, String>
    ) {
        retrofit.updateProductsLocationInImportPackage(
            id = id,
            storageLocationIds = storageLocationIds
        )
    }

    override suspend fun updatePendingImportPackage(
        id: String,
        updatedImportPackage: ImportPackages,
    ) {
        retrofit.updatePendingImportPackage(
            id = id,
            updatedImportPackage = updatedImportPackage.convertToResponse(),
        )
    }

    override suspend fun updatePendingExportPackage(
        id: String,
        updatedExportPackage: ExportPackages,
    ) {
        retrofit.updatePendingExportPackage(
            id = id,
            updatedExportPackage = updatedExportPackage.convertToResponse(),
        )
    }

    override suspend fun getPendingExportPackages(): List<ExportPackages> {
        return retrofit.getPendingExportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun addPendingExportPackages(pendingExportPackage: ExportPackagePendingDto) {
        retrofit.addPendingExportPackages(pendingExportPackage = pendingExportPackage)
    }

    override suspend fun getDoneImportPackages(): List<ImportPackages> {
        return retrofit.getDoneImportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun getExportPackageById(id: String): ExportPackages {
        return retrofit.getExportPackageById(id = id).body()?.convertToModel()!!
    }

    override suspend fun approveExportPackage(id: String) {
        return retrofit.approveExportPackage(id)
    }

    override suspend fun getDoneExportPackages(): List<ExportPackages> {
        return retrofit.getAllDoneExportPackages().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun createImportPackage(importPackage: ImportPackages) {
        return retrofit.createImportPackage(importPackage = importPackage.convertToResponse())
    }

    override suspend fun addNewSupplier(supplier: Supplier) {
        return retrofit.addNewSupplier(supplier = supplier.convertToRequest())// ?: "Can't add"
    }

    override suspend fun addNewCustomer(customer: Customer) {
        return retrofit.addNewCustomer(customer = customer.convertToResponse())// ?: "Can't add"
    }

    override suspend fun updateNewCustomer(id: String, customer: Customer) {
        return retrofit.editCustomer(
            id = id,
            customer = customer.convertToResponse()
        )
    }

    override suspend fun login(username: String, password: String): Map<String, String> {
        return retrofit.login(username = username, password = password).body() ?: mapOf()
    }

    override suspend fun getUserDetails(id: String): User {
        return retrofit.getUserDetails(id = id).body()?.convertToModel()!!
    }

    override suspend fun updateUserDetail(id: String, user: User) {
        return retrofit.updateUser(
            id = id,
            updated = user
        )
    }

    override suspend fun addNewUser(user: UserRequest) {
        return retrofit.addNewUser(
            updated = user
        )
    }

    override suspend fun getAllUserDetails(): List<User> {
        return retrofit.getAllUserDetails().body()?.mapNotNull { it.convertToModel() }
            ?: listOf()
    }

    override suspend fun getAllNotificationDetails(): List<Notification> {
        return retrofit.getAllNotificationDetails().body() ?: listOf()
    }

    override suspend fun addNewGenre(genre: Genre) {
        return retrofit.addNewGenre(genre = genre.convertToResponse())// ?: "Can't add"
    }

    override suspend fun getAnswerChatBox(question: String): String {
        return retrofit.getAnswerChatBox(message = MessageChatBoxResponse(question)).body()?.message
            ?: ""
    }

    override suspend fun syncDataChatBox() {
        return retrofit.syncDataChatBox()
    }
}
