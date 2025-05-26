package com.example.warehousemanagement.ui.feature.report

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.network.dto.OverviewReportByMonth
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartPreview
import com.example.warehousemanagement.ui.feature.report.ui.YearPickerScreen
import com.example.warehousemanagement.ui.feature.report.viewModel.OverviewUiState
import com.example.warehousemanagement.ui.feature.report.viewModel.OverviewViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardReportScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val overviewUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val year by viewModel.year.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        YearPickerScreen(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            currentYear = year,
            selectedYear = year,
            onSelectYear = {
                viewModel.updateYear(it)
            }
        )
        when (val result = overviewUiState) {
            is OverviewUiState.Loading -> IndeterminateCircularIndicator()
            is OverviewUiState.Error -> NothingText()
            is OverviewUiState.Success -> {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))

                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFFF3E0),
                                    Color(0xFFFFFBE0)
                                ) // Gradient colors
                            ), // Rounded corners
                        )
                        .padding(10.dp)

                ) {
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
                                    text = "\$${result.cost}",
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
                                    text = "\$${result.revenue}",
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
                StorageLocationReportScreen(
                    listOverview = result.listProfitByYear,
                )
                Row {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xFFFFC46B),
                            painter = painterResource(id = R.drawable.round_icon),
                            contentDescription = ""
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
                            painter = painterResource(id = R.drawable.round_icon),
                            contentDescription = ""
                        )
                        Text(
                            fontSize = 10.sp,
                            text = "Total Export Cost"
                        )
                    }
                }
                TableYear(
                    listOverview = result.listProfitByYear,
                )
            }
        }
    }

}

@Composable
fun TableYear(
    listOverview: List<OverviewReportByMonth>,
) {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        // Header Row
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
    for (index in months.indices) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    if (index % 2 == 0) Color(0xFFF0F0F0) else Color.Transparent
                )
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = months[index],
                modifier = Modifier.weight(1f)
            )
            Text(
                style = if (!listOverview[index].profit && listOverview[index].cost != 0.0) {
                    TextStyle(fontWeight = FontWeight.Bold)
                } else TextStyle(fontWeight = FontWeight.Normal),
                text = "$${listOverview[index].cost}",
                modifier = Modifier.weight(1f)
            )
            Text(
                style = if (listOverview[index].profit && listOverview[index].revenue != 0.0) {
                    TextStyle(fontWeight = FontWeight.Bold)
                } else TextStyle(fontWeight = FontWeight.Normal),
                text = "$${listOverview[index].revenue}",
                modifier = Modifier.weight(1f)
            )
        }
    }
}


