package com.example.warehousemanagement.ui.feature.product

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.example.warehousemanagement.ui.feature.camera.rememberPermissionState
import com.example.warehousemanagement.ui.theme.Dimens
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

@Composable
fun AddProductsByExcel(
    modifier: Modifier = Modifier,
) {
    var fileName by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.PADDING_10_DP),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            UploadExcelButton(
                onFileSelected = { uri ->
                    fileName = uri.toString() // Lưu URI tệp được chọn
                    // TODO: Xử lý tệp Excel tại đây nếu cần
                }
            )

            // Hiển thị tên file đã chọn
            if (fileName.isNotEmpty()) {
                Text("Selected File: $fileName", color = Color.Gray)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Các nút Submit hoặc Add, Previous, Done
        }
    }
}

@Composable
fun UploadExcelButton(
    modifier: Modifier = Modifier,
    onFileSelected: (Uri) -> Unit
) {
//    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        Manifest.permission.READ_MEDIA_IMAGES
//    } else {
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted ->
//            if (isGranted) {
//                // Permission granted, handle accordingly
//            } else {
//                // Permission denied, show a message
//            }
//        }
//    )
//
//    Button(onClick = { launcher.launch(permission) }) {
//        Text("Request Storage Permission")
//    }
    FilePickerScreen()
}

@Composable
fun FilePickerScreen() {
    val context = LocalContext.current
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFileName by remember { mutableStateOf<String?>(null) }


    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
        if (uri != null) {
            readFileFromUri(context, uri)?.let { readExcelFile(it) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(onClick = { filePickerLauncher.launch("*/*") }) {
            Text("Select a file")
        }

        selectedFileName?.let {
            Text(text = "Selected File: $it", modifier = Modifier.padding(top = 8.dp))
        }
    }
}
fun readExcelFile(inputStream: InputStream) {
    val workbook = HSSFWorkbook(inputStream)
    val sheet = workbook.getSheetAt(0) // Lấy sheet đầu tiên

    // Duyệt qua các dòng và cột
    for (row in sheet) {
        for (cell in row) {
            println("Cell value: ${cell.toString()}")
        }
    }

    workbook.close()
}

fun readFileFromUri(context: Context, uri: Uri): InputStream? {
    // Sử dụng ContentResolver để lấy InputStream từ URI
    val contentResolver = context.contentResolver

    // Đảm bảo rằng URI có quyền truy cập
    if (DocumentsContract.isDocumentUri(context, uri)) {
        try {
            return contentResolver.openInputStream(uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}
