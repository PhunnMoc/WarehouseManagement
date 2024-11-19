package com.example.warehousemanagement.ui.feature.product

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun AddProductsByExcel(
    modifier: Modifier = Modifier,
) {
    var fileName by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
        // Nội dung thanh công cụ (như đã định nghĩa trước)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.PADDING_10_DP),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Các phần tử giao diện khác...

            // Nút Upload File Excel
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
    val context = LocalContext.current

    // Tạo launcher để chọn file
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                onFileSelected(uri) // Gửi Uri của file đã chọn
            }
        }
    )

    Button(
        onClick = {
            // Khởi chạy chọn file với các MIME type phù hợp cho Excel
            filePickerLauncher.launch(arrayOf("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        },
        modifier = modifier
    ) {
        Text("Upload Excel File")
    }
}
