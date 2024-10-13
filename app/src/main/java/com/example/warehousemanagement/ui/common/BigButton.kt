package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme

@Composable
fun BigButton(
    labelname: String,
    onClick: () -> Unit // Thêm tham số 'onClick' vào constructor
) {
    Button(
        onClick = onClick, // Gọi hàm 'onClick' khi nhấn nút
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_theme)), // Màu nền
        shape = RoundedCornerShape(50.dp), // Bo tròn các góc
        modifier = Modifier
            .width(200.dp)  // Độ rộng của nút
            .height(50.dp)  // Chiều cao của nút
    ) {
        Text(
            text = labelname,
            fontSize = 10.sp, // Kích thước font chữ
            color = colorResource(id = R.color.text_color_black) // Màu chữ
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    WarehouseManagementTheme {
        BigButton(
            labelname = "Nhấn vào đây",
            onClick = {
                // Xử lý hành động khi nhấn vào nút
                println("Nút đã được nhấn!")
            }
        )
    }
}