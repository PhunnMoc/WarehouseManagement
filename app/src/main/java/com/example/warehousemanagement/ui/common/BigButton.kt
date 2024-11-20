package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme

@Composable
fun BigButton(
    labelname: String, modifier: Modifier = Modifier, enabled: Boolean = false, onClick: () -> Unit
) {
    Button(
        enabled = enabled, onClick = onClick, // Gọi hàm 'onClick' khi nhấn nút
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_theme)), // Màu nền
        shape = RoundedCornerShape(30.dp), // Bo tròn các góc
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = Dimens.PADDING_10_DP,
                horizontal = Dimens.PADDING_40_DP
            ),
            text = labelname,
            fontSize = 20.sp, // Kích thước font chữ
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    WarehouseManagementTheme {
        BigButton(labelname = "Nhấn vào đây", onClick = {
            // Xử lý hành động khi nhấn vào nút
            println("Nút đã được nhấn!")
        })
    }
}