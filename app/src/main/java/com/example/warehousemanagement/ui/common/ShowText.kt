package com.example.warehousemanagement.ui.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShowText(message: String) {
    // Lấy context từ Compose
    val context = LocalContext.current

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

@Preview(showBackground = true)
@Composable
fun PreviewShowToastExample() {
    ShowText("")
}
