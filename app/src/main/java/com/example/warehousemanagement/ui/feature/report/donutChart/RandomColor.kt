package com.example.warehousemanagement.ui.feature.report.donutChart

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val colorList = listOf(
    Color(0xFFFFB5A5), // Red
    Color(0xFFA6FFB6), // Green
    Color(0xFF9DAEFF), // Blue
    Color(0xFFFFEB9C), // Yellow
    Color(0xFFB0FFF2), // Purple
    Color(0xFFFF8376), // Crimson
    Color(0xFFFFB4E1), // Emerald
    Color(0xFF67BDF7), // Sky Blue
    Color(0xFFFFC46B), // Orange
    Color(0xFFFECCFF)  // Deep Purple
)

// Function to randomly select a color
fun getRandomColor(): Color {
    return colorList[Random.nextInt(colorList.size)]
}
