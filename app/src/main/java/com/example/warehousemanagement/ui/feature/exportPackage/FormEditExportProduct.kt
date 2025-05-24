package com.example.warehousemanagement.ui.feature.exportPackage

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
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
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.DetailExportUiState
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.DetailExportViewModel
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.FormAddExportedProductViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEditExportProduct(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: FormAddExportedProductViewModel = hiltViewModel(),
    viewModelDetail: DetailExportViewModel = hiltViewModel()
) {
    val message by viewModel.message.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Hiển thị Toast khi có message
    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val listProduct by viewModel.searchProductUiState.collectAsStateWithLifecycle()
    val detailExportUiState by viewModelDetail.detailExportPackageUiState.collectAsStateWithLifecycle()
    when (val detail = detailExportUiState) {
        is DetailExportUiState.Loading -> IndeterminateCircularIndicator()
        is DetailExportUiState.Error -> NothingText()
        is DetailExportUiState.Success -> {

            var exportProductList by remember { mutableStateOf(detail.detailExportPackage.listProduct.toFormExportProductDataList()) }

            var packageName by remember { mutableStateOf(detail.detailExportPackage.packageName) }
            var packageDescription by remember { mutableStateOf(detail.detailExportPackage.note) }
            var customer by remember { mutableStateOf(detail.detailExportPackage.customer) }
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
                                    listProducts = exportProductList.convertToMap(),
                                    note = packageDescription ?: "",
                                    packageName = packageName,
                                    date = LocalDate.now().atStartOfDay()
                                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                    customer = customer,
                                )
                                onBackClick()
                            })
                    }
                }) { innerPadding ->
                CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {

                    FormExportProducts(
                        modifier = Modifier.padding(innerPadding),
                        upDateStatusSubmitButton = {
                            shouldEnableSubmitButton = it
                        },
                        onRemove = { index ->
                            exportProductList =
                                exportProductList.toMutableList().apply { removeAt(index) }
                        },
                        onExportProductChange = { updatedProduct, index ->
                            exportProductList = exportProductList.toMutableList()
                                .apply { set(index, updatedProduct) }
                        },
                        exportProductList = exportProductList,
                        onAddOneMoreExportProduct = {
                            exportProductList = exportProductList.toMutableList()
                                .apply { add(FormExportProductData()) }
                        },
                        packageName = packageName,
                        packageDescription = packageDescription ?: "",
                        onUpdatePackageName = {
                            packageName = it
                        },
                        onUpdatePackageDescription = {
                            packageDescription = it
                        },
                        customer = customer,
                        onUpdateCustomer = {
                            customer = it
                        },
                    )
                }
            }
        }
    }
}