package com.example.warehousemanagement


import android.content.res.AssetFileDescriptor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.warehousemanagement.ui.navigation.AppNavigation
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme
import com.example.warehousemanagement.ui.feature.camera.objectDetect.YuvToRgbConverter
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            cameraExecutor = Executors.newSingleThreadExecutor()
            WarehouseManagementTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation(
                    cameraExecutor = cameraExecutor,
                    yuvToRgbConverter = yuvToRgbConverter,
                    interpreter = interpreter,
                    labels = labels
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    //-----------------------------------------------------------------------
    companion object {
        private const val MODEL_FILE_NAME = "ssd_mobilenet_v1_1_metadata_1.tflite"
        private const val LABEL_FILE_NAME = "coco_dataset_labels_v1.txt"
    }

    lateinit var cameraExecutor: ExecutorService

    val interpreter: Interpreter by lazy {
        Interpreter(loadModel())
    }

    val labels: List<String> by lazy {
        loadLabels()
    }

    val yuvToRgbConverter: YuvToRgbConverter by lazy {
        YuvToRgbConverter(this)
    }

    private fun loadModel(fileName: String = MODEL_FILE_NAME): ByteBuffer {
        lateinit var modelBuffer: ByteBuffer
        var file: AssetFileDescriptor? = null
        try {
            file = assets.openFd(fileName)
            val inputStream = FileInputStream(file.fileDescriptor)
            val fileChannel = inputStream.channel
            modelBuffer = fileChannel.map(
                FileChannel.MapMode.READ_ONLY,
                file.startOffset,
                file.declaredLength
            )
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading model", Toast.LENGTH_SHORT).show()
            finish()
        } finally {
            file?.close()
        }
        return modelBuffer
    }

    private fun loadLabels(fileName: String = LABEL_FILE_NAME): List<String> {
        var labels = listOf<String>()
        var inputStream: InputStream? = null
        try {
            inputStream = assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            labels = reader.readLines()
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading model", Toast.LENGTH_SHORT).show()
            finish()
        } finally {
            inputStream?.close()
        }
        return labels
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WarehouseManagementTheme {
        Greeting("Android")

    }
}


