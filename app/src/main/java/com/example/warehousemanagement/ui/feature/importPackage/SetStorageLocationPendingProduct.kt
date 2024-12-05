package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

@Composable
fun SetStorageLocationPendingProduct(
    navigateToImportPackageScreen: () -> Unit,
    viewModel: SetStorageLocationProductViewModel = hiltViewModel()
) {
    val listPendingProduct by viewModel.listPendingProduct.collectAsStateWithLifecycle()
    var isShowBottomSheet by remember {
        mutableStateOf(false)
    }
    var currentId by remember {
        mutableStateOf("")
    }
    when (val listPendingProduct = listPendingProduct) {
        is SetStorageLocationProductUiState.Loading -> IndeterminateCircularIndicator()
        is SetStorageLocationProductUiState.Error -> NothingText()
        is SetStorageLocationProductUiState.Success -> {
            LazyColumn {
                items(listPendingProduct.listProductPending) { product ->
                    ProductItemDetail(product = product, onEditClick = {
                        currentId = it
                        isShowBottomSheet = true
                    })
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { /* Handle Cancel action */ },
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                        ) {
                            Text(text = "Cancel")
                        }
//                        Button(
//                            onClick = { /* Handle Decline action */ },
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(16.dp),
//                        ) {
//                            Text(text = "Decline")
//                        }
                        Button(
                            onClick = {
                                viewModel.updateAllProduct()
                                navigateToImportPackageScreen()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                        ) {
                            Text(text = "Approve")
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
