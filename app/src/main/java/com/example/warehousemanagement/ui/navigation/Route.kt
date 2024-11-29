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
    object QRCodeScanner : Routes()

    /////

    @Serializable
    object StorageLocation : Routes()

    @Serializable
    data class StorageLocationDetail(val idStorageLocation: String) : Routes()

    //Product
    @Serializable
    object Products : Routes()

    @Serializable
    data class Product(val id: String) : Routes()

    @Serializable
    data class AddProducts(val packageName: String) : Routes()

    @Serializable
    object AddProductByExcel : Routes()

    @Serializable
    object SearchProduct : Routes()

    @Serializable
    object SearchGenre : Routes()

    @Serializable
    object SearchStorageLocation : Routes()

    ///
    @Serializable
    object Genres : Routes()

    ///
    @Serializable
    object ImportPackage : Routes()

    ///
    @Serializable
    object Suppliers : Routes()

    @Serializable
    data class Supplier(val idSupplier: String) : Routes()

    @Serializable
    object AddSuppliers : Routes()

    @Serializable
    object Customers : Routes()

    @Serializable
    data class Customer(val idCustomer: String) : Routes()

    @Serializable
    object AddCustomers : Routes()

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