package com.example.warehousemanagement.ui.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import com.example.warehousemanagement.ui.feature.home.AdminScreen
import com.example.warehousemanagement.ui.navigation.scaleIntoContainer
import com.example.warehousemanagement.ui.navigation.scaleOutOfContainer
import kotlinx.serialization.Serializable

@Serializable
object HomeAdmin

@Serializable
object HomeWorker
class HomeNavigation {
}

fun NavGraphBuilder. {

}
composable<Routes.HomeAdmin>(enterTransition = {
    scaleIntoContainer()
}, exitTransition = {
    scaleOutOfContainer()
}) {
    AdminScreen(
        onNavigateToProduct = { navigationController.navigate(Routes.Products) },
        onNavigateToStorageLocation = { navigationController.navigate(Routes.StorageLocation) },
        onNavigateToGenre = { navigationController.navigate(Routes.Products) },
        onNavigateToCustomer = { /*TODO*/ },
        onNavigateToSupplier = { /*TODO*/ })

}