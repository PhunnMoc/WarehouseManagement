package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.warehousemanagement.ui.feature.filter.FilterByGenreId
import com.example.warehousemanagement.ui.feature.filter.FilterBySellingPrice
import com.example.warehousemanagement.ui.feature.filter.FilterByStorageLocationId
import com.example.warehousemanagement.ui.feature.filter.FilterBySupplierId
import com.example.warehousemanagement.ui.feature.genre.viewModel.GenreUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.SetStorageLocationProductUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.SetStorageLocationProductViewModel
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationViewModel
import com.example.warehousemanagement.ui.feature.supplier.viewModel.SupplierUIState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetStorageLocationPendingProduct(
    modifier: Modifier = Modifier,
    navigateToImportPackageScreen: () -> Unit,
    viewModel: SetStorageLocationProductViewModel = hiltViewModel()
) {
    val listPendingProduct by viewModel.listPendingProduct.collectAsStateWithLifecycle()
    val mapProductAndStorage by viewModel.mapProductAndStorage.collectAsStateWithLifecycle()
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    var currentId by remember {
        mutableStateOf("")
    }
    Scaffold(
        containerColor = colorResource(id = R.color.line_light_gray),
        topBar = {
            HeaderOfScreen(
                containerColor = Color.Transparent,
                mainTitleText = "Set storage location",
                endContent = {
                    IconButton(
                        modifier = Modifier.size(25.dp),
                        onClick = { navigateToImportPackageScreen() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icons8_delete),
                            contentDescription = ""
                        )
                    }
                },
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BigButton(
                    onClick = {
                        viewModel.updateAllProduct()
                        navigateToImportPackageScreen()
                    },
                    enabled = true,
                    modifier = Modifier
                        .weight(1f),
                    labelname = "Approve"
                )
            }
        }
    ) { innerPadding ->
        CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
            when (val listPendingProduct = listPendingProduct) {
                is SetStorageLocationProductUiState.Loading -> IndeterminateCircularIndicator()
                is SetStorageLocationProductUiState.Error -> NothingText()
                is SetStorageLocationProductUiState.Success -> {
                    LazyColumn(
                        modifier = modifier.padding(innerPadding)
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
                                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .padding(vertical = 10.dp, horizontal = 15.dp)
                                    .animateContentSize(animationSpec = tween(durationMillis = 500)), // Animation kích thước
                            ) {
                                listPendingProduct.listProductPending.forEach { product ->
                                    ProductItemDetail(
                                        modifier = Modifier,
                                        product = product,
                                    )
                                    BigButton(
                                        enabled = true,
                                        onClick = {
                                            currentId = product.idProduct
                                            isShowBottomSheet = true
                                        },
                                        labelname = "Storage Location " + "${
                                            mapProductAndStorage[product.idProduct]
                                        }"
                                    )
                                }

                            }
                        }
                    }
                }
            }
            if (isShowBottomSheet) {
                ChooseSupplierIdBottomSheet(
                    currentId = currentId,
                    onClickStorageLocation = viewModel::addProductAndStorage,
                    onDismiss = { isShowBottomSheet = false })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseSupplierIdBottomSheet(
    currentId: String,
    onClickStorageLocation: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModelStorageLocation: StorageLocationViewModel = hiltViewModel()
) {
    val listStorageLocation by viewModelStorageLocation.storageLocationUiState.collectAsStateWithLifecycle()
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            FilterByStorageLocationId(
                listStorageLocation = (listStorageLocation as? StorageLocationUiState.Success)?.listStorageLocation
                    ?: listOf(),
                onClickFilter = { _, idStorageLoaction ->
                    onClickStorageLocation(currentId, idStorageLoaction)
                    onDismiss()
                },
            )
        }
    }
}
