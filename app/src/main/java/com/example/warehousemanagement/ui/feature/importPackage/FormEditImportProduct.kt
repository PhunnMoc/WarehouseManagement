package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.LocalTextStyle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportViewModel
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.FormImportPackageViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FormEditImportProduct(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    navigateToAddNewSupplier: () -> Unit,
    navigateToAddNewGenre: () -> Unit,
    viewModel: FormImportPackageViewModel = hiltViewModel(),
    viewModelDetail: DetailImportViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val listGenre by viewModel.searchGenreUiState.collectAsStateWithLifecycle()
    val listSupplier by viewModel.searchSupplierUiState.collectAsStateWithLifecycle()
    val detailImportUiState by viewModelDetail.detailPendingImportPackageUiState.collectAsStateWithLifecycle()
    when (val detail = detailImportUiState) {
        is DetailImportUiState.Loading -> IndeterminateCircularIndicator()
        is DetailImportUiState.Error -> NothingText()
        is DetailImportUiState.Success -> {

            var productList by remember { mutableStateOf(detail.detailImportPackage.listProducts.toFormImportProductDataList()) }

            var packageName by remember { mutableStateOf(detail.detailImportPackage.packageName) }
            var packageDescription by remember { mutableStateOf(detail.detailImportPackage.note) }
            var shouldEnableSubmitButton by remember { mutableStateOf(false) }


            Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colorResource(id = R.color.line_light_gray),
                topBar = {
                    HeaderOfScreen(
                        containerColor = Color.Transparent,
                        mainTitleText = stringResource(id = R.string.screen_import_main_title),
                        startContent = {
                            Image(painter = painterResource(id = R.drawable.icons8_back),
                                contentDescription = "Back",
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        onBackClick()
                                    })
                        },
                        endContent = {},
                        scrollBehavior = scrollBehavior
                    )
                },
                bottomBar = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.line_light_gray))
                            .padding(Dimens.PADDING_10_DP),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BigButton(modifier = Modifier.fillMaxWidth(),
                            enabled = shouldEnableSubmitButton,
                            labelname = "Save",
                            onClick = {
                                viewModel.updatePackage(
                                    listProducts = productList.map { it.toProduct() },
                                    note = packageDescription,
                                    packageName = packageName,
                                    date = LocalDate.now().atStartOfDay()
                                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                )
                                onBackClick()
                            })
                    }
                }
            ) { innerPadding ->
                CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {

                    FormImportProducts(
                        modifier = Modifier.padding(innerPadding),
                        listSupplier = listSupplier,
                        listGenre = listGenre,
                        upDateStatusSubmitButton = {
                            shouldEnableSubmitButton = it
                        },
                        onRemove = { index ->
                            productList = productList.toMutableList().apply { removeAt(index) }
                        },
                        onProductChange = { updatedProduct, index ->
                            productList =
                                productList.toMutableList().apply { set(index, updatedProduct) }
                        },
                        productList = productList,
                        onAddOneMoreProduct = {
                            productList =
                                productList.toMutableList().apply { add(FormImportProductData()) }
                        },
                        packageName = packageName,
                        packageDescription = packageDescription,
                        onUpdatePackageName = {
                            packageName = it
                        },
                        onUpdatePackageDescription = {
                            packageDescription = it
                        },
                        navigateToAddNewSupplier = navigateToAddNewSupplier,
                        navigateToAddNewGenre = navigateToAddNewGenre,
                    )
                }
            }
        }
    }
}