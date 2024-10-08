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
fun BigButton(name: String) {
    Button(
        onClick = { /* TODO: Handle click */ },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_theme)), // Yellow color
        shape = RoundedCornerShape(15.dp), // Rounded corners
        modifier = Modifier
            .width(200.dp)  // Width of the button
            .height(50.dp)  // Height of the button
    ) {
        Text(
            text = name,
            fontSize = 10.sp, // Font size
            color = colorResource(id = R.color.text_color_black) // Text color
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BigButtonPreview() {
    WarehouseManagementTheme {
        BigButton(name = "ADD PRODUCT")
    }
}