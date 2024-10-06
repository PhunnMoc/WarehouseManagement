package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FunctionContainer(isAdmin: Boolean) {
    if (isAdmin) {
        ItemFunction("Admin Function")
    } else {
        ItemFunction("User Function")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFunctionContainer() {
    FunctionContainer(isAdmin = true)
}