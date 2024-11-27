package com.example.warehousemanagement.ui.common.enumClass

import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionItem

enum class EnumFunctionItem(val functionName: String) {
    PRODUCT("Product"),
    STORAGE_LOCATION("Storage_location"),
    GENRE("Genre"),
    CUSTOMER("Customer"),
    SUPPLIER("Supplier"),
    IMPORT_PACKAGE("Import package"),
    EXPORT_PACKAGE("Export package"),
}

val functionItemsAdmin: List<FunctionItem> = listOf(
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.PRODUCT,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.STORAGE_LOCATION,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.GENRE,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.CUSTOMER,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.SUPPLIER,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.IMPORT_PACKAGE,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.EXPORT_PACKAGE,
        iconResource = R.drawable.storage_location,
    )
)

val functionItemsWorker: List<FunctionItem> = listOf(
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.PRODUCT,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.STORAGE_LOCATION,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.GENRE,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.CUSTOMER,
        iconResource = R.drawable.storage_location,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.SUPPLIER,
        iconResource = R.drawable.storage_location,
    )
)

