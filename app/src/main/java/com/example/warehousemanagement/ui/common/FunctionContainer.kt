package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
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
fun FunctionRow(functionItems: List<FunctionItem>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        functionItems.forEach { item ->
            ItemFunction(
                functionName = item.enumFunctionItem.functionName,
                iconResource = item.iconResource,
                shape = RoundedCornerShape(8.dp),
                textSize = 10.sp,
                onClick = { /*TODO: Handle button click*/ }
            )
        }
    }
}

@Composable
fun AdminView(
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
        items(functionItemsAdmin) { item ->
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
fun NonAdminView(modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.padding(5.dp))
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.employee_subtitle_shortcut_function),
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold

        )
        FunctionRow(functionItemsWorker)
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
    Column {
        if (isAdmin) {
            AdminView(
                onNavigateToProduct = onNavigateToProduct,
                onNavigateToStorageLocation = onNavigateToStorageLocation,
                onNavigateToGenre = onNavigateToGenre,
                onNavigateToCustomer = onNavigateToCustomer,
                onNavigateToSupplier = onNavigateToSupplier,
                onNavigateToImportPackage = onNavigateToImportPackage,
                onNavigateToExportPackage = onNavigateToExportPackage,
                onNavigateToManagerUser = onNavigateToManagerUser,
            )
        } else {
            NonAdminView(modifier = modifier)
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