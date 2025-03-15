package com.example.warehousemanagement.ui.common.enumClass

import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionItem

enum class EnumFunctionItem(val functionName: String) {
    PRODUCT("Product"),
    STORAGE_LOCATION("Location"),
    GENRE("Genre"),
    CUSTOMER("Customer"),
    SUPPLIER("Supplier"),
    IMPORT_PACKAGE("Import package"),
    EXPORT_PACKAGE("Export package"),
    MANGER_USER("Manager Account"),
}

val functionItemsAdmin: List<FunctionItem> = listOf(
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.PRODUCT,
        iconResource = R.drawable.products,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.STORAGE_LOCATION,
        iconResource = R.drawable.location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.GENRE,
        iconResource = R.drawable.genre,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.CUSTOMER,
        iconResource = R.drawable.customer,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.SUPPLIER,
        iconResource = R.drawable.supplier,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.IMPORT_PACKAGE,
        iconResource = R.drawable.import_package,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.EXPORT_PACKAGE,
        iconResource = R.drawable.export_package,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.MANGER_USER,
        iconResource = R.drawable.customer_ic,
    )
)

val functionItemsWorker: List<FunctionItem> = listOf(
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.PRODUCT,
        iconResource = R.drawable.products,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.STORAGE_LOCATION,
        iconResource = R.drawable.location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.GENRE,
        iconResource = R.drawable.genre,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.CUSTOMER,
        iconResource = R.drawable.customer,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.SUPPLIER,
        iconResource = R.drawable.supplier,
    )
)

