package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ItemFunction(functionName: String) {
    Text(text = functionName)
}

@Preview(showBackground = true)
@Composable
fun PreviewItemFunction() {
    ItemFunction("Function")
}