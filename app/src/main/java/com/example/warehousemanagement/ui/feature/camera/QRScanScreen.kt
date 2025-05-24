package com.example.warehousemanagement.ui.feature.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Composable
fun QRCodeScannerScreen(
    onNavigateToProductDetail: (String) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var hasNavigated by remember { mutableStateOf(false) }

    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val previewView = remember { PreviewView(context) }

    LaunchedEffect(cameraPermissionState.hasPermission) {
        if (!cameraPermissionState.hasPermission) {
            cameraPermissionState.launchPermissionRequest()
        }
        hasNavigated = false // Reset lại trạng thái khi quyền camera thay đổi
    }

    if (cameraPermissionState.hasPermission) {
        // Giải phóng tài nguyên camera khi rời khỏi màn hình
        DisposableEffect(key1 = cameraProviderFuture) {
            var cameraProvider: ProcessCameraProvider? = null
            val executor = Executors.newSingleThreadExecutor()

            // Khởi tạo camera
            cameraProviderFuture.addListener({
                cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(executor) { imageProxy ->
                    if (!hasNavigated) {
                        processImageProxy(imageProxy, barcodeScanner) { barcodeResult ->
                            if (!hasNavigated) {
                                hasNavigated = true
                                imageProxy.close()
                                onNavigateToProductDetail(barcodeResult)
                            }
                        }
                    } else {
                        imageProxy.close()
                    }
                }

                preview.surfaceProvider = previewView.surfaceProvider
                cameraProvider?.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
            }, ContextCompat.getMainExecutor(context))

            // 🛠️ **Giải phóng tài nguyên khi rời khỏi màn hình**
            onDispose {
                cameraProvider?.unbindAll() // Giải phóng camera
                executor.shutdown() // Tắt executor
            }
        }

        // Hiển thị preview camera
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )

    } else {
        Text("Camera permission is required")
    }
}


@Composable
fun OverlayScanningArea() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Vẽ một khung vuông cho khu vực quét
            drawRect(
                color = Color.Transparent,
                topLeft = Offset(100f, 100f), // Điều chỉnh vị trí
                size = Size(200f, 200f), // Điều chỉnh kích thước khu vực quét
                style = Stroke(width = 5f)
            )
        }
    }
}

@OptIn(ExperimentalGetImage::class)
fun processImageProxy(
    imageProxy: ImageProxy,
    barcodeScanner: BarcodeScanner,
    onResult: (String) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val inputImage = InputImage.fromMediaImage(mediaImage, 0)
        barcodeScanner.process(inputImage)
            .addOnSuccessListener { barcodes ->
                // Nếu quét thành công, trả về ID của QR code
                for (barcode in barcodes) {
                    val value = barcode.displayValue
                    if (value != null) {
                        onResult(value) // Trả về ID QR
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("CameraX", "Barcode scanning failed: ${e.message}")
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}

@Composable
private fun rememberPermissionState(permission: String): PermissionState {
    val context = LocalContext.current
    return remember {
        PermissionState(
            context = context,
            permission = permission,
            hasPermission = ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
}

data class PermissionState(
    val permission: String,
    var hasPermission: Boolean,
    val context: Context,
) {
    fun launchPermissionRequest() {
        ActivityCompat.requestPermissions(
            context as ComponentActivity,
            arrayOf(permission),
            1
        )
    }
}