package com.example.warehousemanagement.ui.feature.report.viewModel

import com.example.warehousemanagement.R

enum class NavigationItem(
    val title: String,
    val icon: Int
) {
    StorageLocation(
        icon = R.drawable.location,
        title = "Storage Location"
    ),
    Income(
        icon = R.drawable.export_package,
        title = "Income"
    ),
    OutCome(
        icon = R.drawable.import_package,
        title = "OutCome"
    ),
}

enum class CustomDrawerState {
    Opened,
    Closed
}

fun CustomDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun CustomDrawerState.opposite(): CustomDrawerState {
    return if (this == CustomDrawerState.Opened) CustomDrawerState.Closed
    else CustomDrawerState.Opened
}