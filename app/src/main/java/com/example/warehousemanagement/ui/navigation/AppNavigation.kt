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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.repository.PreferencesRepository
import com.example.warehousemanagement.ui.feature.camera.QRCodeScannerScreen
import com.example.warehousemanagement.ui.feature.camera.objectDetect.DetectionScreen
import com.example.warehousemanagement.ui.feature.customer.CustomersScreen
import com.example.warehousemanagement.ui.feature.customer.DetailCustomer
import com.example.warehousemanagement.ui.feature.customer.FormAddOrEditCustomerForm
import com.example.warehousemanagement.ui.feature.exportPackage.DetailExportPackage
import com.example.warehousemanagement.ui.feature.exportPackage.ExportPackageScreen
import com.example.warehousemanagement.ui.feature.exportPackage.FormAddExportedProduct
import com.example.warehousemanagement.ui.feature.exportPackage.FormEditExportProduct
import com.example.warehousemanagement.ui.feature.genre.FormAddOrEditGenreForm
import com.example.warehousemanagement.ui.feature.genre.GenreScreen
import com.example.warehousemanagement.ui.feature.home.AdminScreen
import com.example.warehousemanagement.ui.feature.home.WorkerScreen
import com.example.warehousemanagement.ui.feature.importPackage.DetailDoneImportPackage
import com.example.warehousemanagement.ui.feature.importPackage.DetailPendingImportPackage
import com.example.warehousemanagement.ui.feature.importPackage.FormAddOrEditProductForm
import com.example.warehousemanagement.ui.feature.importPackage.FormEditImportProduct
import com.example.warehousemanagement.ui.feature.importPackage.ImportPackageScreen
import com.example.warehousemanagement.ui.feature.importPackage.SetStorageLocationPendingProduct
import com.example.warehousemanagement.ui.feature.login.LoginScreen
import com.example.warehousemanagement.ui.feature.notification.NotificationScreen
import com.example.warehousemanagement.ui.feature.product.AddProductsByExcel
import com.example.warehousemanagement.ui.feature.product.DetailProduct
import com.example.warehousemanagement.ui.feature.product.ProductsScreen
import com.example.warehousemanagement.ui.feature.report.ReportScreen
import com.example.warehousemanagement.ui.feature.search.SearchCustomerScreen
import com.example.warehousemanagement.ui.feature.search.SearchGenreScreen
import com.example.warehousemanagement.ui.feature.search.SearchProductScreen
import com.example.warehousemanagement.ui.feature.search.SearchStorageLocation
import com.example.warehousemanagement.ui.feature.search.SearchSupplierScreen
import com.example.warehousemanagement.ui.feature.setting.SettingScreen
import com.example.warehousemanagement.ui.feature.supplier.DetailSupplier
import com.example.warehousemanagement.ui.feature.supplier.FormAddOrEditSupplierForm
import com.example.warehousemanagement.ui.feature.supplier.SuppliersScreen
import com.example.warehousemanagement.ui.feature.user.ManagerUserScreen
import com.example.warehousemanagement.ui.feature.user.UserDetailSreen
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.size_icon_30
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.warehousemanagement.ui.feature.camera.objectDetect.YuvToRgbConverter
import com.example.warehousemanagement.ui.feature.chatBox.ChatBoxScreen
import com.example.warehousemanagement.ui.feature.customer.EditCustomerScreen
import com.example.warehousemanagement.ui.feature.storage.ProductBelongStorageScreen
import com.example.warehousemanagement.ui.feature.supplier.EditSupplierScreen
import com.example.warehousemanagement.ui.feature.user.AddNewUser
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import org.tensorflow.lite.Interpreter
import java.util.concurrent.ExecutorService

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier,
    homeRoute: Routes,
) {

    val iconNav = listOf(
        R.drawable.icons8_home__1_,
        R.drawable.icons8_setting,
        R.drawable.icons8_search,
    )
    val topLevelDestinations = if (homeRoute == Routes.HomeAdmin) {
        listOf(
            TopLevelDestinations.HomeAdmin,
            TopLevelDestinations.Analyze,
            TopLevelDestinations.Setting
        )
    } else {
        listOf(
            TopLevelDestinations.HomeWorker,
            TopLevelDestinations.Analyze,
            TopLevelDestinations.Setting
        )
    }

    NavigationBar(
        modifier = Modifier.shadow(elevation = Dimens.PADDING_20_DP, shape = RectangleShape),
        containerColor = colorResource(id = R.color.icon_tint_white),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        topLevelDestinations.map { screen ->
            NavigationBarItem(
                label = {
                    Text(
                        fontFamily = QuickSand, fontWeight = FontWeight.W600, text = screen.label
                    )
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
    preferencesRepository: PreferencesRepository = hiltEntryPoint().preferencesRepository(),
    cameraExecutor: ExecutorService,
    yuvToRgbConverter: YuvToRgbConverter,
    interpreter: Interpreter,
    labels: List<String>
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true,
    )
    val navigationController = rememberNavController()
    var isShowNavigation by remember {
        mutableStateOf(true)
    }

    val token by preferencesRepository.getAccessToken().collectAsState(initial = null)
    val role by preferencesRepository.getUserRole().collectAsState(initial = null)
    val startDestination = if (!token.isNullOrEmpty()) {
        if (role == "ADMIN") {
            TopLevelDestinations.HomeAdmin.route
        } else {
            TopLevelDestinations.HomeWorker.route
        }
    } else Routes.Login

    Scaffold(containerColor = colorResource(id = R.color.icon_tint_white), bottomBar = {
        if (isShowNavigation) {
            BottomBar(
                homeRoute = startDestination,
                navController = navigationController,
            )
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navigationController,
                startDestination = startDestination,
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
                        onNavigateToChatbox = { navigationController.navigate(Routes.ChatBox("")) },
                        onNavigateToScranQrScreen = { navigationController.navigate(Routes.QRCodeScanner) },
                        onNavigateToProduct = { navigationController.navigate(Routes.Products) },
                        onNavigateToStorageLocation = { navigationController.navigate(Routes.StorageLocation) },
                        onNavigateToGenre = { navigationController.navigate(Routes.Genres) },
                        onNavigateToImportPackage = { navigationController.navigate(Routes.ImportPackage) },
                        onNavigateToExportPackage = { navigationController.navigate(Routes.ExportPackage) },
                        onNavigateToCustomer = { navigationController.navigate(Routes.Customers) },
                        onNavigateToSupplier = { navigationController.navigate(Routes.Suppliers) },
                        onNavigateNotification = { navigationController.navigate(Routes.Notification) },
                        onNavigateToManagerUser = { navigationController.navigate(Routes.ManagerUsers) },
                    )
                    isShowNavigation = true
                }

                composable<Routes.HomeWorker> {
                    WorkerScreen(
                        onNavigateToChatbox = { navigationController.navigate(Routes.ChatBox("")) },
                        onNavigateToScranQrScreen = { navigationController.navigate(Routes.QRCodeScanner) },
                        onNavigateToProduct = { navigationController.navigate(Routes.Products) },
                        onNavigateToStorageLocation = { navigationController.navigate(Routes.StorageLocation) },
                        onNavigateToGenre = { navigationController.navigate(Routes.Genres) },
                        onNavigateToImportPackage = { navigationController.navigate(Routes.ImportPackage) },
                        onNavigateToExportPackage = { navigationController.navigate(Routes.ExportPackage) },
                        onNavigateToCustomer = { navigationController.navigate(Routes.Customers) },
                        onNavigateToSupplier = { navigationController.navigate(Routes.Suppliers) },
                        onNavigateNotification = { navigationController.navigate(Routes.Notification) },
                        onNavigateToManagerUser = { navigationController.navigate(Routes.ManagerUsers) },
                    )
                    isShowNavigation = true
                }


                composable<Routes.Setting> {
                    SettingScreen()
                    isShowNavigation = true
                }

                composable<Routes.ChatBox> {
                    ChatBoxScreen(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false
                }

                composable<Routes.Analyze> {
                    ReportScreen(
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },
                        navigateToExportDetail = { id ->
                            navigationController.navigate(
                                Routes.EditExportPackages(id = id)
                            )
                        },
                        navigateToImportDetail = { id ->
                            navigationController.navigate(
                                Routes.EditImportPackages(id = id)
                            )
                        }
                    )
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
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },
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

                composable<Routes.AddImportPackages> {
                    FormAddOrEditProductForm(
                        navigateToAddNewGenre = { navigationController.navigate(Routes.AddGenres) },
                        navigateToAddNewSupplier = { navigationController.navigate(Routes.AddSuppliers) },
                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.EditImportPackages> {
                    FormEditImportProduct(
                        navigateToAddNewGenre = { navigationController.navigate(Routes.AddGenres) },
                        navigateToAddNewSupplier = { navigationController.navigate(Routes.AddSuppliers) },
                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.EditExportPackages> {
                    FormEditExportProduct(
                        navigateToAddNewCustomer = { navigationController.navigate(Routes.AddCustomers) },
                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }

                composable<Routes.AddExportPackages> {
                    FormAddExportedProduct(
                        navigateToAddNewCustomer = { navigationController.navigate(Routes.AddCustomers) },
                        onNavigationBack = { navigationController.popBackStack() })
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
                    SearchStorageLocation(
                        onBackClick = { navigationController.popBackStack() },
                        onClickDetailStorageLocation = { id ->
                            navigationController.navigate(Routes.DetailStorageLocation(id))
                        },
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
                                Routes.DetailStorageLocation(
                                    id = idStorage
                                )
                            )
                        },
                        onClickSearch = { navigationController.navigate(Routes.SearchStorageLocation) },

                        )
                    isShowNavigation = false
                }
                composable<Routes.DetailStorageLocation> {
                    ProductBelongStorageScreen(
                        onNavigationBack = { navigationController.popBackStack() },
                        onNavigationDetailProduct = {
                            navigationController.navigate(Routes.Product(it))
                        }
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
                    FormAddOrEditGenreForm(onSubmit = {},
                        onBackClick = { navigationController.popBackStack() })
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
                    ImportPackageScreen(onClickAddProduct = { navigationController.navigate(Routes.AddImportPackages) },
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },
                        onClickAddProductByExcel = { navigationController.navigate(Routes.AddProductByExcel) },
                        onBackClick = { navigationController.popBackStack() },
                        onClickSearch = { /*TODO*/ },
                        onNavigationDetailPendingImportPackage = { id ->
                            navigationController.navigate(
                                Routes.DetailPendingImportPackage(id = id)
                            )
                        },
                        onNavigationDetailDoneImportPackage = { id ->
                            navigationController.navigate(
                                Routes.DetailDoneImportPackage(id = id)
                            )
                        },
                        onNavigationEditImportPackages = {
                            navigationController.navigate(
                                Routes.EditImportPackages(id = it)
                            )
                        })
                    isShowNavigation = false
                }

                composable<Routes.DetailPendingImportPackage> {
                    DetailPendingImportPackage(
                        onBack = { navigationController.popBackStack() },
                        navigateToSetStorageLocationScreen = { id ->
                            navigationController.navigate(
                                Routes.SetStorageImportPackage(id = id)
                            )
                        },
                        openObjectCounting = { id, quantity ->
                            navigationController.navigate(
                                Routes.Detection(
                                    id = id,
                                    quantity = quantity,
                                )
                            )
                        },
                    )
                }
                composable<Routes.DetailDoneImportPackage> {
                    DetailDoneImportPackage(onBack = { navigationController.popBackStack() },
                        navigateToSetStorageLocationScreen = { id ->
                            navigationController.navigate(
                                Routes.SetStorageImportPackage(id = id)
                            )
                        })
                }
                composable<Routes.DetailExportPackage> {
                    DetailExportPackage(
                        onBack = { navigationController.popBackStack() },
                    )
                }
                composable<Routes.SetStorageImportPackage> {
                    SetStorageLocationPendingProduct(navigateToImportPackageScreen = {
                        navigationController.navigate(
                            Routes.ImportPackage
                        ) {
                            popUpTo<Routes.SetStorageImportPackage> {
                                inclusive = true
                            }
                        }
                    })
                }

                composable<Routes.ExportPackage> {
                    ExportPackageScreen(onClickAddExportForm = {
                        navigationController.navigate(Routes.AddExportPackages)
                    },
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },

                        onNavigationDetailExportPackage = { id ->
                            navigationController.navigate(
                                Routes.DetailExportPackage(id = id)
                            )
                        },
                        onClickAddProductByExcel = { navigationController.navigate(Routes.AddProductByExcel) },
                        onBackClick = { navigationController.navigate(startDestination) },
                        onEditPendingPackage = {
                            navigationController.navigate(
                                Routes.EditExportPackages(id = it)
                            )
                        })
                    isShowNavigation = false
                }

                composable<Routes.Suppliers> {
                    SuppliersScreen(onBackClick = { navigationController.popBackStack() },
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },
                        onClickSearch = { navigationController.navigate(Routes.SearchSupplier) },
                        onClickAddSupplier = { navigationController.navigate(Routes.AddSuppliers) },
                        onNavigationDetailSupplier = { id ->
                            navigationController.navigate(
                                Routes.Supplier(
                                    idSupplier = id
                                )
                            )
                        },
                        onNavigationEditSupplierScreen = { id ->
                            navigationController.navigate(
                                Routes.EditSupplier(
                                    idSupplier = id,
                                )
                            )
                        })

                    isShowNavigation = false
                }
                composable<Routes.Supplier> { backStackEntry ->
                    //  val product: Routes.Product = backStackEntry.toRoute()
                    DetailSupplier(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false

                }
                composable<Routes.EditSupplier> { backStackEntry ->
                    EditSupplierScreen(
                        onBackClick = { navigationController.popBackStack() },
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
                        onNavigationDetailCustomer = { id ->
                            navigationController.navigate(
                                Routes.Customer(
                                    id = id
                                )
                            )
                        },
                        onNavigateToEditCustomerScreen = { id ->
                            navigationController.navigate(
                                Routes.EditCustomer(
                                    id = id
                                )
                            )
                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.SearchCustomer> {
                    SearchCustomerScreen(
                        onBackClick = {
                            navigationController.popBackStack(
                                route = Routes.SearchCustomer,
                                inclusive = false,
                            )
                        },
                        onClickDetailCustomer = { id ->
                            navigationController.navigate(
                                Routes.Customer(
                                    id = id
                                )
                            )
                        },
                    )

                    isShowNavigation = false
                }
                composable<Routes.Customer> { backStackEntry ->
                    //  val product: Routes.Product = backStackEntry.toRoute()
                    DetailCustomer(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false
                }
                composable<Routes.EditCustomer> { backStackEntry ->
                    //  val product: Routes.Product = backStackEntry.toRoute()
                    EditCustomerScreen(
                        onBackClick = { navigationController.popBackStack() },
                    )
                    isShowNavigation = false
                }
                composable<Routes.AddCustomers> {
                    FormAddOrEditCustomerForm(onSubmit = {},
                        onBackClick = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.ManagerUsers> {
                    ManagerUserScreen(onNavigateToUserDetail = { id ->
                        navigationController.navigate(
                            Routes.UserDetail(
                                id = id
                            )
                        )
                    },
                        onNavigateToChatBox = { navigationController.navigate(Routes.ChatBox(it)) },
                        onNavigationBack = { navigationController.popBackStack() },
                        onNavigateToAddNewUser = { navigationController.navigate(Routes.AddNewUser) }
                    )
                    isShowNavigation = false
                }
                composable<Routes.AddNewUser> {
                    AddNewUser(onNavigationBack = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.UserDetail> {
                    UserDetailSreen(onNavigationBack = { navigationController.popBackStack() })
                    isShowNavigation = false
                }
                composable<Routes.Detection> {
                    DetectionScreen(
                        cameraExecutor = cameraExecutor,
                        yuvToRgbConverter = yuvToRgbConverter,
                        interpreter = interpreter,
                        labels = labels,
                        onBack = {
                            navigationController.popBackStack()
                        }
                    )
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