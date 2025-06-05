package com.example.warehousemanagement.ui.navigation

import com.example.warehousemanagement.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Login : Routes()

    @Serializable
    object HomeAdmin : Routes()

    @Serializable
    object HomeWorker : Routes()

    @Serializable
    object Analyze : Routes()

    @Serializable
    object Setting : Routes()

    @Serializable
    data class ChatBox(val message: String) : Routes()

    @Serializable
    object Notification : Routes()

    @Serializable
    object QRCodeScanner : Routes()

    @Serializable
    data class Detection(val id: String, val quantity: Int) : Routes()

    /////

    @Serializable
    object StorageLocation : Routes()

    @Serializable
    data class DetailStorageLocation(val id: String) : Routes()

    //Product
    @Serializable
    object Products : Routes()

    @Serializable
    data class Product(val id: String) : Routes()

    @Serializable
    object AddImportPackages : Routes()

    @Serializable
    data class EditImportPackages(
        val id: String,
    ) : Routes()

    @Serializable
    data class EditExportPackages(
        val id: String,
    ) : Routes()

    @Serializable
    object AddExportPackages : Routes()

    @Serializable
    object AddProductByExcel : Routes()

    @Serializable
    object SearchProduct : Routes()


    @Serializable
    object SearchStorageLocation : Routes()

    ///
    @Serializable
    object Genres : Routes()

    @Serializable
    data class Genre(val idGenre: String) : Routes()

    @Serializable
    object AddGenres : Routes()

    @Serializable
    object SearchGenre : Routes()

    ///
    @Serializable
    object ImportPackage : Routes()

    @Serializable
    data class DetailPendingImportPackage(val id: String) : Routes()

    @Serializable
    data class DetailDoneImportPackage(val id: String) : Routes()

    @Serializable
    data class DetailExportPackage(val id: String) : Routes()

    @Serializable
    data class SetStorageImportPackage(val id: String) : Routes()

    @Serializable
    object ExportPackage : Routes()

    ///
    @Serializable
    object Suppliers : Routes()

    @Serializable
    data class Supplier(val idSupplier: String) : Routes()

    @Serializable
    data class EditSupplier(val idSupplier: String) : Routes()

    @Serializable
    object SearchSupplier : Routes()

    @Serializable
    object AddSuppliers : Routes()

    @Serializable
    object Customers : Routes()

    @Serializable
    data class Customer(val id: String) : Routes()

    @Serializable
    data class EditCustomer(val id: String) : Routes()

    @Serializable
    object SearchCustomer : Routes()


    @Serializable
    object AddCustomers : Routes()

    @Serializable
    object ManagerUsers : Routes()

    @Serializable
    object AddNewUser : Routes()

    @Serializable
    data class UserDetail(val id: String) : Routes()

}

enum class TopLevelDestinations(
    val label: String,
    val icon: Int,
    val route: Routes,
) {
    HomeAdmin(
        label = "Home",
        icon = R.drawable.icons8_home__1_,
        route = Routes.HomeAdmin,
    ),
    HomeWorker(
        label = "Home",
        icon = R.drawable.icons8_home__1_,
        route = Routes.HomeWorker,
    ),
    Analyze(
        label = "Analyze",
        icon = R.drawable.icons8_bell,
        route = Routes.Analyze,
    ),
    Setting(
        label = "Setting",
        icon = R.drawable.icons8_setting,
        route = Routes.Setting,
    ),
}