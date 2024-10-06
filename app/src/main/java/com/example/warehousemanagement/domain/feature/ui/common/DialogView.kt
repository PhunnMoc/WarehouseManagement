package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogView(
    title: String,
    message: String,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(text = cancelText)
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewDialogView() {
    DialogView(
        title = "Dialog Title",
        message = "This is a message",
        confirmText = "Confirm",
        cancelText = "Cancel",
        onConfirm = { /*TODO: Handle confirm action*/ },
        onCancel = { /*TODO: Handle cancel action*/ }
    )
}