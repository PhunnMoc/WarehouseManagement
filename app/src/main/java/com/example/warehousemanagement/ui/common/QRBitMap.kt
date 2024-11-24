package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.example.warehousemanagement.util.generateQRCode

@Composable
fun QRCodeScreen(id: String) {
    val qrBitmap = generateQRCode(id)
    if (qrBitmap != null) {
        Image(
            painter = BitmapPainter(qrBitmap.asImageBitmap()),
            contentDescription = "QR Code"
        )
    } else {
        Text(text = "Failed to generate QR Code")
    }
}