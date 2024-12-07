package com.example.warehousemanagement.ui.feature.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.warehousemanagement.ui.feature.report.lineChart.PerformanceChart
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun StorageLocationReportScreen() {
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier.padding(5.dp)) {
                PerformanceChart(
                    list = listOf(
                        10f,
                        20f,
                        3f,
                        1f,
                        10f,
                        0f,
                        5f,
                        3f,
                        1f,
                        20f,
                        10f,
                        10f
                    )
                )
                PerformanceChart(
                    list = listOf(
                        15f,
                        16f,
                        10f,
                        30f,
                        3f,
                        0f,
                        0f,
                        8f,
                        20f,
                        15f,
                        20f,
                        20f
                    )
                )

            }
            Text(
                text = " Jan     Feb     Mar     Apr     May     June     July     Aug     Sep     Oct     Nov     Dec",
                fontFamily = QuickSand,
                fontSize = 10.sp
            )
        }

    }
}