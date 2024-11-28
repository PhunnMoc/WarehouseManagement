package com.example.warehousemanagement.ui.common.enumClass

import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionItem

enum class EnumFunctionItem(val functionName: String) {
    PRODUCT("Product"),
    STORAGE_LOCATION("Location"),
    GENRE("Genre"),
    CUSTOMER("Customer"),
    SUPPLIER("Supplier"),
}

val functionItemsAdmin: List<FunctionItem> = listOf(
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.PRODUCT,
        iconResource = R.drawable.products,
    ),
    FunctionItem(
        enumFunctionItem = EnumFunctionItem.STORAGE_LOCATION,
        iconResource = R.drawable.storage_location,
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

