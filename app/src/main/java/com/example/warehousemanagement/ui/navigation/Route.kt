package com.example.warehousemanagement.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.warehousemanagement.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
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

    @Serializable
    object StorageLocation : Routes()

    //Product
    @Serializable
    object Products : Routes()

    @Serializable
    data class Product(val idProduct: String) : Routes()

    @Serializable
    object AddProducts : Routes()

    @Serializable
    object AddProductByExcel : Routes()


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