package com.example.warehousemanagement.ui.navigation

import StorageLocationScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.ui.feature.camera.QRCodeScannerScreen
import com.example.warehousemanagement.ui.feature.customer.CustomersScreen
import com.example.warehousemanagement.ui.feature.customer.FormAddOrEditCustomerForm
import com.example.warehousemanagement.ui.feature.genre.FormAddOrEditGenreForm
import com.example.warehousemanagement.ui.feature.exportPackage.ExportPackageScreen
import com.example.warehousemanagement.ui.feature.genre.GenreScreen
import com.example.warehousemanagement.ui.feature.home.AdminScreen
import com.example.warehousemanagement.ui.feature.importPackage.ImportPackageScreen
import com.example.warehousemanagement.ui.feature.product.AddProductsByExcel
import com.example.warehousemanagement.ui.feature.product.DetailProduct
import com.example.warehousemanagement.ui.feature.importPackage.FormAddOrEditProductForm
import com.example.warehousemanagement.ui.feature.login.LoginScreen
import com.example.warehousemanagement.ui.feature.notification.NotificationScreen
import com.example.warehousemanagement.ui.feature.product.ProductsScreen
import com.example.warehousemanagement.ui.feature.search.SearchGenreScreen
import com.example.warehousemanagement.ui.feature.search.SearchCustomerScreen
import com.example.warehousemanagement.ui.feature.search.SearchGenreScreen
import com.example.warehousemanagement.ui.feature.search.SearchProductScreen
import com.example.warehousemanagement.ui.feature.setting.SettingScreen
import com.example.warehousemanagement.ui.feature.search.SearchSupplierScreen
import com.example.warehousemanagement.ui.feature.supplier.FormAddOrEditSupplierForm
import com.example.warehousemanagement.ui.feature.supplier.SuppliersScreen
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.size_icon_30
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch

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

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPoint {
    fun preferencesRepository(): PreferencesRepository
}

@Composable
fun hiltEntryPoint(): AppEntryPoint {
    val context = LocalContext.current
    return EntryPointAccessors.fromApplication(context, AppEntryPoint::class.java)
}

@Composable
fun AppNavigation(
    preferencesRepository: PreferencesRepository = hiltEntryPoint().preferencesRepository()
) {
    val navigationController = rememberNavController()
    var isShowNavigation by remember {
        mutableStateOf(true)
    }
    val token by preferencesRepository.getAccessToken().collectAsState(initial = null)
    val startDestination =
        if (!token.isNullOrEmpty()) TopLevelDestinations.HomeAdmin.route else Routes.Login

    Scaffold(containerColor = colorResource(id = R.color.icon_tint_white), bottomBar = {
        if (isShowNavigation) {
            BottomBar(
                navController = navigationController,
            )
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navigationController,
                startDestination = TopLevelDestinations.HomeAdmin.route,
                enterTransition = {
                    scaleIntoContainer()
                },
                exitTransition = {
                    scaleOutOfContainer()
                }) {

                composable<Routes.Login> {
                    LoginScreen(onNavigationToHome = { navigationController.navigate(Routes.HomeAdmin) })
                    isShowNavigation = false
                }

                composable<Routes.HomeAdmin> {
                    AdminScreen(
                        onNavigateToScranQrScreen = { navigationController.navigate(Routes.QRCodeScanner) },
                        onNavigateToProduct = { navigationController.navigate(Routes.Products) },
                        onNavigateToStorageLocation = { navigationController.navigate(Routes.StorageLocation) },
                        onNavigateToGenre = { navigationController.navigate(Routes.Genres) },
                        onNavigateToImportPackage = { navigationController.navigate(Routes.ImportPackage) },
                        onNavigateToExportPackage = { navigationController.navigate(Routes.ExportPackage) },
                        onNavigateToCustomer = { navigationController.navigate(Routes.Customers) },
                        onNavigateToSupplier = { navigationController.navigate(Routes.Suppliers) },
                        onNavigateNotification = { navigationController.navigate(Routes.Notification) },
                    )
                    isShowNavigation = true
                }

                composable<Routes.HomeWorker> {
                    Text(text = "Home")
                    isShowNavigation = true
                }


                composable<Routes.Setting> {
                    SettingScreen()
                    isShowNavigation = true
                }

                composable<Routes.Analyze> {
                    Text(text = "Analyze")
                    isShowNavigation = true

                }
                composable<Routes.Notification> {
                    NotificationScreen(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false

                }
                composable<Routes.QRCodeScanner> {
                    QRCodeScannerScreen(onNavigateToProductDetail = { id ->
                        navigationController.navigate(
                            Routes.Product(
                                id = id
                            )
                        )
                    })
                    isShowNavigation = false

                }

                composable<Routes.Products> {
                    ProductsScreen(onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { navigationController.navigate(Routes.SearchProduct) },
                        onNavigationDetailProduct = { id ->
                            navigationController.navigate(
                                Routes.Product(
                                    id = id
                                )
                            )
                        })
                    // AddProductsByExcel()
                    isShowNavigation = false
                }

                composable<Routes.Product> { backStackEntry ->
                    //  val product: Routes.Product = backStackEntry.toRoute()
                    DetailProduct(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false

                }

                composable<Routes.AddProducts> {
                    FormAddOrEditProductForm(onSubmit = {}, onAdd1MoreProduct = { packageName ->
                        navigationController.navigate(
                            Routes.AddProducts(
                                packageName = packageName
                            )
                        )
                    },

                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }

                composable<Routes.AddProductByExcel> {
                    AddProductsByExcel(
//                        onSubmit = {},
//                        onAdd1MoreProduct = { navigationController.navigate(Routes.AddProducts) },
//                        onBackClick = { navigationController.popBackStack() }
                    )
                    isShowNavigation = false
                }

                composable<Routes.SearchGenre> {
                    SearchGenreScreen(
//                        onSubmit = {},
//                        onAdd1MoreProduct = { navigationController.navigate(Routes.AddProducts) },
                        onBackClick = { navigationController.popBackStack() },
//                        onClickDetailGenre = { id ->
//                            TODO()
////                            navigationController.navigate(
////
////                            )
//                        },
                    )
                    isShowNavigation = false
                }
                composable<Routes.SearchStorageLocation> {
                    SearchGenreScreen(
//                        onSubmit = {},
//                        onAdd1MoreProduct = { navigationController.navigate(Routes.AddProducts) },
                        onBackClick = { navigationController.popBackStack() },
//                        onClickDetailGenre = { id ->
//                            TODO()
////                            navigationController.navigate(
////
////                            )
//                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.SearchProduct> {
                    SearchProductScreen(
//                        onSubmit = {},
//                        onAdd1MoreProduct = { navigationController.navigate(Routes.AddProducts) },
                        onBackClick = { navigationController.popBackStack() },
                        onClickDetailProduct = { id ->
                            navigationController.navigate(
                                Routes.Product(
                                    id = id
                                )
                            )
                        },
                    )

                    isShowNavigation = false
                }

                composable<Routes.StorageLocation> {
                    StorageLocationScreen(
                        onNavigationBack = { navigationController.popBackStack() },
                        onNavigationDetail = { idStorage ->
                            navigationController.navigate(
                                Routes.StorageLocationDetail(
                                    idStorageLocation = idStorage
                                )
                            )
                        },
                        onClickSearch = { navigationController.navigate(Routes.SearchStorageLocation) },
                    )
                    isShowNavigation = false
                }

                composable<Routes.Genres> {
                    GenreScreen(
                        onClickSearch = { navigationController.navigate(Routes.SearchGenre) },
                        onNavigationBack = { navigationController.popBackStack() },
                        onClickAddGenre = { navigationController.navigate(Routes.AddGenres) },
                        )
                    isShowNavigation = false
                }
                composable<Routes.AddGenres> {
                    FormAddOrEditGenreForm(
                        onSubmit = {},
                        onBackClick = { navigationController.popBackStack() }
                    )
                    isShowNavigation = false
                }
                composable<Routes.SearchGenre> {
                    SearchGenreScreen(
                        onBackClick = { navigationController.popBackStack() },
//                        onClickDetailGenre = { id ->
//                            navigationController.navigate(
//                                Routes.Genre(
//                                    idGenre = id
//                                )
//                            )
//                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.ImportPackage> {
                    ImportPackageScreen(onClickAddProduct = { packageName ->
                        navigationController.navigate(
                            Routes.AddProducts(
                                packageName = packageName
                            )
                        )
                    },
                        onClickAddProductByExcel = { navigationController.navigate(Routes.AddProductByExcel) },
                        onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { /*TODO*/ },
                        onNavigationDetailImportPackage = {})
                    isShowNavigation = false
                }

                composable<Routes.ExportPackage> {
                    ExportPackageScreen(onClickAddProduct = { packageName ->
                        navigationController.navigate(
                            Routes.AddProducts(
                                packageName = packageName
                            )
                        )
                    },
                        onClickAddProductByExcel = { navigationController.navigate(Routes.AddProductByExcel) },
                        onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { /*TODO*/ },
                        onNavigationDetailExportPackages = {})
                    isShowNavigation = false
                }

                composable<Routes.Suppliers> {
                    SuppliersScreen(
                        onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { navigationController.navigate(Routes.SearchSupplier) },
                        onClickAddSupplier = { navigationController.navigate(Routes.AddSuppliers) },
                    )

                    isShowNavigation = false
                }
                composable<Routes.SearchSupplier> {
                    SearchSupplierScreen(
                        onBackClick = { navigationController.popBackStack() },
                        onClickDetailSupplier = { id ->
                            navigationController.navigate(
                                Routes.Supplier(
                                    idSupplier = id
                                )
                            )
                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.AddSuppliers> {
                    FormAddOrEditSupplierForm(onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.Customers> {
                    CustomersScreen(
                        onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { navigationController.navigate(Routes.SearchCustomer) },
                        onClickAddCustomer = { navigationController.navigate(Routes.AddCustomers) },
                    )

                    isShowNavigation = false
                }
                composable<Routes.SearchCustomer> {
                    SearchCustomerScreen(
                        onBackClick = { navigationController.popBackStack() },
                        onClickDetailCustomer = { id ->
                            navigationController.navigate(
                                Routes.Customer(
                                    idCustomer = id
                                )
                            )
                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.AddCustomers> {
                    FormAddOrEditCustomerForm(onSubmit = {},
                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
            }
        }
    }
}



fun scaleIntoContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
    initialScale: Float = if (direction == ScaleTransitionDirection.OUTWARDS) 0.9f else 1.1f
): EnterTransition {
    return scaleIn(
        animationSpec = tween(220, delayMillis = 90), initialScale = initialScale
    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
}

fun scaleOutOfContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
    targetScale: Float = if (direction == ScaleTransitionDirection.INWARDS) 0.9f else 1.1f
): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 220, delayMillis = 90
        ), targetScale = targetScale
    ) + fadeOut(tween(delayMillis = 90))
}

enum class ScaleTransitionDirection {
    INWARDS, OUTWARDS
}