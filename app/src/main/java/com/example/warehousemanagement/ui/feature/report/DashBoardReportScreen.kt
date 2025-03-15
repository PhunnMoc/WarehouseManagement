package com.example.warehousemanagement.ui.feature.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartPreview
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun DashBoardReportScreen() {
    Column {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))

                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFFF3E0), Color(0xFFFFFBE0)) // Gradient colors
                    ), // Rounded corners
                )
                .padding(10.dp)

        ) {
            Text(
                text = "This month: December",
                fontSize = 15.sp,
                fontFamily = QuickSand,
                fontWeight = FontWeight.W600,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(Dimens.PADDING_5_DP)
                        .weight(1f)
                        .height(80.dp)
                        .background(
                            color = Color(0xB3FFFFFF),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
//                    Icon(
//                        painter = painterResource(id = R.drawable.import_package),
//                        contentDescription = ""
//                    )
                        Text(
                            text = "\$550000",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(text = "Out", fontFamily = QuickSand)
                }
                Column(
                    modifier = Modifier
                        .padding(Dimens.PADDING_5_DP)
                        .weight(1f)
                        .height(80.dp)
                        .background(
                            color = Color(0xB3FFFFFF),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
//                    Icon(
//                        painter = painterResource(id = R.drawable.export_package),
//                        contentDescription = ""
//                    )
                        Text(
                            text = "\$854050",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(text = "In", fontFamily = QuickSand)
                }
            }
        }
        Text(
            modifier = Modifier.padding(20.dp),
            text = "This year 2024",
            fontSize = 15.sp,
            fontFamily = QuickSand,
            fontWeight = FontWeight.W600,
        )
        StorageLocationReportScreen()
        Row {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFFFFC46B),
                    painter = painterResource(id = R.drawable.round_icon), contentDescription = ""
                )
                Text(
                    fontSize = 10.sp, text = "Total Import Cost"
                )
            }
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF67BDF7),
                    painter = painterResource(id = R.drawable.round_icon), contentDescription = ""
                )
                Text(
                    fontSize = 10.sp,
                    text = "Total Export Cost"
                )
            }
        }
        TableYear()
    }
}

@Composable
fun TableYear() {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    val importCosts = List(12) { (1000..5000).random() }
    val exportCosts = List(12) { (2000..6000).random() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Month",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Import Cost",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Export Cost",
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Data Rows
        item {
            for (index in months.indices) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            if (index % 2 == 0) Color.LightGray else Color.Transparent
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = months[index],
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$${importCosts[index]}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$${exportCosts[index]}",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
