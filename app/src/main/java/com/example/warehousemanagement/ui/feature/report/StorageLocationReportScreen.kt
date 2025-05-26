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
import com.example.warehousemanagement.data.network.dto.OverviewReportByMonth
import com.example.warehousemanagement.ui.feature.report.lineChart.PerformanceChart
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun StorageLocationReportScreen(
    listOverview: List<OverviewReportByMonth>
) {
    val listRevenue = listOverview.map { it.revenue.toFloat() }
    val listCost = listOverview.map { it.cost.toFloat() }
    val max = maxOf(listRevenue.max(), listCost.max())
    val min = minOf(listRevenue.min(), listCost.min())
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier.padding(5.dp)) {
                PerformanceChart(
                    max = max,
                    min = min,
                    list = listCost,
                    lineColor = Color(0xFFFFC46B),
                )
                PerformanceChart(
                    max = max,
                    min = min,
                    list = listRevenue,
                    lineColor = Color(0xFF67BDF7),
                )

            }
        }

    }
}