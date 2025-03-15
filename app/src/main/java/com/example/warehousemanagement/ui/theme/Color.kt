package com.example.warehousemanagement.ui.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.warehousemanagement.R

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Theme = Color(0xFFFFBE15)

@Composable
fun customTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = colorResource(id = R.color.background_theme),
        backgroundColor = Color.White, // Nền màu trắng
        focusedBorderColor = colorResource(id = R.color.text_color_dark_gray), // Màu viền khi được chọn
        unfocusedBorderColor = colorResource(id = R.color.line_gray), // Màu viền khi không được chọn
        placeholderColor = colorResource(id = R.color.line_gray),  // Màu placeholder khi không focus
        focusedLabelColor = colorResource(id = R.color.background_light_theme),  // Màu chữ của label khi focus
        unfocusedLabelColor = Color.Gray, // Màu chữ của label khi không focus
        textColor = Color.Black // Màu chữ khi focus,

    )
}

