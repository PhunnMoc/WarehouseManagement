package com.example.warehousemanagement.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.LuminanceSource
import androidx.camera.core.ImageProxy
import android.graphics.Color

object BitmapUtils {
    fun luminanceSourceToBitmap(source: LuminanceSource): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val grayValue = source.getRow(y, null)[x].toInt() and 0xFF
                pixels[y * width + x] = Color.rgb(grayValue, grayValue, grayValue)
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }
}


class ImageProxyToLuminanceSource(imageProxy: ImageProxy) : LuminanceSource(imageProxy.width, imageProxy.height) {
    private val luminanceData: ByteArray

    init {
        val buffer = imageProxy.planes[0].buffer
        luminanceData = ByteArray(buffer.capacity())
        buffer.get(luminanceData)
    }

    override fun getRow(y: Int, row: ByteArray?): ByteArray {
        val width = width
        val rowData = row ?: ByteArray(width)
        System.arraycopy(luminanceData, y * width, rowData, 0, width)
        return rowData
    }

    override fun getMatrix(): ByteArray {
        return luminanceData
    }
}

fun generateQRCode(id: String): Bitmap? {
    val size = 150
    val qrCodeWriter = QRCodeWriter()
    return try {
        val bitMatrix: BitMatrix = qrCodeWriter.encode(id, BarcodeFormat.QR_CODE, size, size)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }
        bitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}
