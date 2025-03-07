package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.customTextFieldColors

@Composable
fun InputArea(
    label: String = "Enter text here",
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                fontFamily = QuickSand,
                text = label
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp), // Adjust the height for the input area
        shape = RoundedCornerShape(10.dp), // Rounded corners
        textStyle = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
        colors = customTextFieldColors(),
        maxLines = Int.MAX_VALUE, // Allow unlimited lines
        singleLine = false, // Multiline input
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default) // Default IME options
    )
}
