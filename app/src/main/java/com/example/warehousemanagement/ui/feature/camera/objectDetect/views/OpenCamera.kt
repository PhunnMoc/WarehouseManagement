package com.example.warehousemanagement.ui.feature.camera.objectDetect.views

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.mlr_apps.objectdetection.ObjectDetector
import com.example.warehousemanagement.ui.feature.camera.objectDetect.YuvToRgbConverter
import org.tensorflow.lite.Interpreter
import java.util.concurrent.ExecutorService


//----------------------------- CAMERA --------------------------------------

@Composable
fun OpenCamera(
    cameraExecutor: ExecutorService,
    yuvToRgbConverter: YuvToRgbConverter,
    interpreter: Interpreter,
    labels: List<String>
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Column {
        CameraPreview(
            context = context,
            lifecycleOwner = lifecycleOwner,
            cameraExecutor = cameraExecutor,
            yuvToRgbConverter = yuvToRgbConverter,
            interpreter = interpreter,
            labels = labels
        )
    }
}

@Composable
fun CameraPreview(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    cameraExecutor: ExecutorService,
    yuvToRgbConverter: YuvToRgbConverter,
    interpreter: Interpreter,
    labels: List<String>,
    viewModel: DetectionViewModel = hiltViewModel()
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProvider = cameraProviderFuture.get()

    val drawCanvas by remember { viewModel.isLoading }
    val detectionListObject by remember { viewModel.detectionList }

    val paint = Paint()
    val pathColorList = listOf(Color.Red, Color.Green, Color.Cyan, Color.Blue)
    val pathColorListInt = listOf(
        android.graphics.Color.RED,
        android.graphics.Color.GREEN,
        android.graphics.Color.CYAN,
        android.graphics.Color.BLUE
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val boxConstraint = this
        val sizeWith = with(LocalDensity.current) { boxConstraint.maxWidth.toPx() }
        val sizeHeight = with(LocalDensity.current) { boxConstraint.maxHeight.toPx() }
        Text(
            modifier = Modifier.zIndex(1f),
            color = Color.Red  ,
            fontWeight = FontWeight.Bold,
            text = detectionListObject.size.toString()
        )
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                cameraProviderFuture.addListener({

                    val imageAnalyzer = ImageAnalysis.Builder()
                        .setTargetRotation(Surface.ROTATION_0)
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(
                                cameraExecutor,
                                ObjectDetector(
                                    yuvToRgbConverter = yuvToRgbConverter,
                                    interpreter = interpreter,
                                    labels = labels,
                                    resultViewSize = android.util.Size(
                                        sizeWith.toInt(), sizeHeight.toInt()
                                    )
                                ) { detectedObjectList ->
                                    viewModel.setList(detectedObjectList)
                                }
                            )
                        }

                    imageCapture = ImageCapture.Builder()
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageCapture,
                        preview,
                        imageAnalyzer
                    )
                }, executor)
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )

        if (drawCanvas) {
            Canvas(
                modifier = Modifier.fillMaxSize(),
                onDraw = {

                    detectionListObject.mapIndexed { i, detectionObject ->
                        Log.d("Object", detectionObject.label + " --- " + detectionObject.score)
                        paint.apply {
                            color = pathColorListInt[i]
                            style = Paint.Style.FILL
                            isAntiAlias = true
                            textSize = 50f
                        }

                        drawRect(
                            color = pathColorList[i],
                            topLeft = Offset(
                                x = detectionObject.boundingBox.left,
                                y = detectionObject.boundingBox.top
                            ),
                            size = Size(
                                width = detectionObject.boundingBox.width(),
                                height = detectionObject.boundingBox.height()
                            ),
                            style = Stroke(width = 3.dp.toPx())
                        )

                        drawIntoCanvas {
                            it.nativeCanvas.drawText(
                                detectionObject.label + " " + "%,.2f".format(detectionObject.score * 100) + "%",
                                detectionObject.boundingBox.left,            // x-coordinates of the origin (top left)
                                detectionObject.boundingBox.top - 5f, // y-coordinates of the origin (top left)
                                paint
                            )
                        }
                    }
                }
            )
        }
    }
}

