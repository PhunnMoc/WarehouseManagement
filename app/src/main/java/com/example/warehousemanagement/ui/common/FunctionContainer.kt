package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.enumClass.EnumFunctionItem
import com.example.warehousemanagement.ui.common.enumClass.functionItemsAdmin
import com.example.warehousemanagement.ui.common.enumClass.functionItemsWorker
import com.example.warehousemanagement.ui.theme.Dimens

data class FunctionItem(
    val enumFunctionItem: EnumFunctionItem,
    val iconResource: Int,
)

@Composable
fun FunctionRow(
    onNavigateToProduct: () -> Unit,
    onNavigateToStorageLocation: () -> Unit,
    onNavigateToGenre: () -> Unit,
    onNavigateToCustomer: () -> Unit,
    onNavigateToSupplier: () -> Unit,
    onNavigateToImportPackage: () -> Unit,
    onNavigateToExportPackage: () -> Unit,
    onNavigateToManagerUser: () -> Unit,
    modifier: Modifier = Modifier,
    functionItems: List<FunctionItem>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(Dimens.PADDING_95_DP),
        contentPadding = PaddingValues(vertical = Dimens.PADDING_10_DP),
    ) {
        items(functionItems) { item ->
            val onNavigate = when (item.enumFunctionItem) {
                EnumFunctionItem.PRODUCT -> onNavigateToProduct
                EnumFunctionItem.STORAGE_LOCATION -> onNavigateToStorageLocation
                EnumFunctionItem.GENRE -> onNavigateToGenre
                EnumFunctionItem.CUSTOMER -> onNavigateToCustomer
                EnumFunctionItem.SUPPLIER -> onNavigateToSupplier
                EnumFunctionItem.IMPORT_PACKAGE -> onNavigateToImportPackage
                EnumFunctionItem.EXPORT_PACKAGE -> onNavigateToExportPackage
                EnumFunctionItem.MANGER_USER -> onNavigateToManagerUser
            }
            ItemFunction(
                functionName = item.enumFunctionItem.functionName,
                iconResource = item.iconResource,
                shape = RoundedCornerShape(8.dp),
                textSize = 10.sp,
                onClick = { onNavigate() }
            )
        }
    }
}

@Composable
fun FunctionItemContainerView(
    functionItems: List<FunctionItem>,
    onNavigateToProduct: () -> Unit,
    onNavigateToStorageLocation: () -> Unit,
    onNavigateToGenre: () -> Unit,
    onNavigateToCustomer: () -> Unit,
    onNavigateToSupplier: () -> Unit,
    onNavigateToImportPackage: () -> Unit,
    onNavigateToExportPackage: () -> Unit,
    onNavigateToManagerUser: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Text(
        text = stringResource(id = R.string.admin_subtitle_all_function),
        modifier = Modifier.padding(vertical = Dimens.PADDING_5_DP),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold

    )
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(Dimens.PADDING_95_DP),
        contentPadding = PaddingValues(vertical = Dimens.PADDING_10_DP),
    ) {
        items(functionItems) { item ->
            val onNavigate = when (item.enumFunctionItem) {
                EnumFunctionItem.PRODUCT -> onNavigateToProduct
                EnumFunctionItem.STORAGE_LOCATION -> onNavigateToStorageLocation
                EnumFunctionItem.GENRE -> onNavigateToGenre
                EnumFunctionItem.CUSTOMER -> onNavigateToCustomer
                EnumFunctionItem.SUPPLIER -> onNavigateToSupplier
                EnumFunctionItem.IMPORT_PACKAGE -> onNavigateToImportPackage
                EnumFunctionItem.EXPORT_PACKAGE -> onNavigateToExportPackage
                EnumFunctionItem.MANGER_USER -> onNavigateToManagerUser
            }
            ItemFunction(
                functionName = item.enumFunctionItem.functionName,
                iconResource = item.iconResource,
                shape = RoundedCornerShape(8.dp),
                textSize = 10.sp,
                onClick = { onNavigate() }
            )
        }
    }
}

@Composable
fun FunctionContainer(
    onNavigateToProduct: () -> Unit,
    onNavigateToStorageLocation: () -> Unit,
    onNavigateToGenre: () -> Unit,
    onNavigateToCustomer: () -> Unit,
    onNavigateToSupplier: () -> Unit,
    onNavigateToImportPackage: () -> Unit,
    onNavigateToExportPackage: () -> Unit,
    onNavigateToManagerUser: () -> Unit,
    modifier: Modifier = Modifier,
    isAdmin: Boolean
) {
    Column(modifier = modifier) {
        if (isAdmin) {
            FunctionItemContainerView(
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToStorageLocation = onNavigateToStorageLocation,
                onNavigateToGenre = onNavigateToGenre,
                onNavigateToCustomer = onNavigateToCustomer,
                onNavigateToSupplier = onNavigateToSupplier,
                onNavigateToImportPackage = onNavigateToImportPackage,
                onNavigateToExportPackage = onNavigateToExportPackage,
                onNavigateToManagerUser = onNavigateToManagerUser,
                functionItems = functionItemsAdmin,
            )
        } else {
            FunctionItemContainerView(
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToStorageLocation = onNavigateToStorageLocation,
                onNavigateToGenre = onNavigateToGenre,
                onNavigateToCustomer = onNavigateToCustomer,
                onNavigateToSupplier = onNavigateToSupplier,
                onNavigateToImportPackage = onNavigateToImportPackage,
                onNavigateToExportPackage = onNavigateToExportPackage,
                onNavigateToManagerUser = onNavigateToManagerUser,
                functionItems = functionItemsWorker,
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewFunctionContainer() {
//    FunctionContainer(
//        onNavigateToProduct = {},
//        onNavigateToStorageLocation = {},
//        onNavigateToGenre = {},
//        onNavigateToCustomer = {},
//        onNavigateToSupplier = {},
//        isAdmin = true
//    )
//}