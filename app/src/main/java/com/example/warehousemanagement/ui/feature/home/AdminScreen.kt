package com.example.warehousemanagement.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun HalfIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        alpha = 0.3f,
        painter = painterResource(id = R.drawable.package_image),
        contentDescription = "Half Opened Package",
        modifier = modifier.zIndex(-1f),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    onNavigateToScranQrScreen: () -> Unit,
    onNavigateToProduct: () -> Unit,
    onNavigateToStorageLocation: () -> Unit,
    onNavigateToGenre: () -> Unit,
    onNavigateToCustomer: () -> Unit,
    onNavigateToSupplier: () -> Unit,
    onNavigateToImportPackage: () -> Unit,
    onNavigateToExportPackage: () -> Unit,
    onNavigateNotification: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(
                mainTitleText = stringResource(id = R.string.screen_home_admin_main_title),
                endContent = {
                    WrapIcon(
                        tint = colorResource(id = R.color.icon_tint_gray),
                        modifier = Modifier.size(Dimens.SIZE_ICON_30_DP),
                        idIcon = R.drawable.icons8_bell,
                        isNewNotification = false,
                        onClickIcon = onNavigateNotification,
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }) { innerpadding ->
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .padding(innerpadding)
                    // .fillMaxSize()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                val listSuggestions = listOf("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
                Row(
                    modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WrapIcon(
                        idIcon = R.drawable.scan_icon,
                        tint = colorResource(id = R.color.icon_tint_gray),
                        modifier = Modifier
                            .size(Dimens.SIZE_ICON_35_DP)
                            .padding(end = Dimens.PADDING_5_DP),
                        onClickIcon = onNavigateToScranQrScreen,
                    )
                    SearchBarWithSuggestion(listSuggestions)
                }

                FunctionContainer(
                    onNavigateToProduct = onNavigateToProduct,
                    onNavigateToStorageLocation = onNavigateToStorageLocation,
                    onNavigateToGenre = onNavigateToGenre,
                    onNavigateToCustomer = onNavigateToCustomer,
                    onNavigateToSupplier = onNavigateToSupplier,
                    onNavigateToImportPackage = onNavigateToImportPackage,
                    onNavigateToExportPackage = onNavigateToExportPackage,
                    isAdmin = true
                )
            }
            HalfIcon(
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAdminActivity() {
//    AdminScreen(
//        onNavigateToProduct = {},
//        onNavigateToStorageLocation = {},
//        onNavigateToGenre = {},
//        onNavigateToCustomer = {},
//        onNavigateToSupplier = {},
//        onNavigateToScranQrScreen = {},
//    )
//}