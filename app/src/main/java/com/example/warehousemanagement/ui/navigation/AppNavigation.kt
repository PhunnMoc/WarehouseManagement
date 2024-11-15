package com.example.warehousemanagement.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.feature.home.AdminScreen
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.size_icon_30

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val screens = listOf(
        Routes.HomeAdmin,
        Routes.Analyze,
        Routes.Setting,
    )

    val iconNav = listOf(
        R.drawable.icons8_home__1_,
        R.drawable.icons8_setting,
        R.drawable.icons8_search,
    )

    NavigationBar(
        modifier = Modifier.shadow(elevation = Dimens.PADDING_20_DP, shape = RectangleShape),
        containerColor = colorResource(id = R.color.icon_tint_white),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        TopLevelDestinations.entries.map { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.label)
                },
                icon = {
                    Icon(
                        modifier = modifier.size(size_icon_30),
                        imageVector = ImageVector.vectorResource(id = screen.icon),
                        contentDescription = ""
                    )
                },
                selected = currentRoute == "com.example.warehousemanagement.ui.navigation.Routes.${screen}",
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = colorResource(id = R.color.icon_tint_gray),
                    unselectedTextColor = colorResource(id = R.color.text_color_light_gray),
                    selectedIconColor = colorResource(id = R.color.background_theme),
                    unselectedIconColor = colorResource(id = R.color.text_color_dark_gray),
                    indicatorColor = colorResource(id = R.color.line_light_gray),
                ),
            )
        }
    }
}

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    Scaffold(containerColor = colorResource(id = R.color.icon_tint_white), bottomBar = {
        BottomBar(
            navController = navigationController,
        )
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navigationController,
                startDestination = TopLevelDestinations.HomeAdmin.route
            ) {
                composable<Routes.HomeAdmin> {
                    AdminScreen()
                }
                composable<Routes.HomeWorker> {
                    Text(text = "Home")
                }
                composable<Routes.Products> {
                    Text(text = "Products")
                }
                composable<Routes.Setting> { Text(text = "Setting") }
                composable<Routes.Analyze> { Text(text = "Analyze") }
                composable<Routes.Product> { backStackEntry ->
                    val product: Routes.Product = backStackEntry.toRoute()
                    Text(text = "Product")
                }
            }
        }
    }
}