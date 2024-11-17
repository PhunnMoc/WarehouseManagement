package com.example.warehousemanagement.ui.navigation

import com.example.warehousemanagement.R
import kotlinx.serialization.Serializable

@Serializable
object Analyze

@Serializable
object Setting


@Serializable
object Genre

@Serializable
object Customer

@Serializable
object Supplier

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