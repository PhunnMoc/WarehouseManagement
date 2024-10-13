package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(labelName: String) {
    var rembersaveble by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = rembersaveble,
        onValueChange = { newValue -> rembersaveble = newValue },
        label = {
            Text(
                text = labelName,
                color = colorResource(id = R.color.text_color_black)
            )
        },
        modifier = Modifier
            .fillMaxWidth()          // Width of the button
            .height(50.dp)           // Chiều rộng toàn phần
            .padding(16.dp),         // Khoảng cách padding
        shape = RoundedCornerShape(50.dp),  // Bo tròn các góc
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.line_light_gray), // Màu viền khi tập trung
            unfocusedBorderColor = Color.Gray // Màu viền khi không tập trung
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    WarehouseManagementTheme {
        TextField("Product name")
    }
}