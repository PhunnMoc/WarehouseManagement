package com.example.warehousemanagement.ui.feature.report.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.network.dto.CostByMonthResponse
import com.example.warehousemanagement.data.network.dto.MonthlyCostResponse
import com.example.warehousemanagement.data.network.dto.MonthlyRevenueResponse
import com.example.warehousemanagement.data.network.dto.RevenueByMonthResponse
import com.example.warehousemanagement.ui.feature.report.viewModel.RevenueViewModel
import com.example.warehousemanagement.ui.theme.QuickSand
import java.util.Calendar
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevenueCostScreen(
    modifier: Modifier = Modifier,
    viewModel: RevenueViewModel = hiltViewModel(),
    navigateToExportDetail: (String) -> Unit,
    navigateToImportDetail: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val revenueUiState by viewModel.revenueByMonthUiState.collectAsStateWithLifecycle()
    val costUiState by viewModel.costByMonthUiState.collectAsStateWithLifecycle()
    val revenueByMonthUiState by viewModel.revenueDetailUiState.collectAsStateWithLifecycle()
    val costDetailUiState by viewModel.costDetailUiState.collectAsStateWithLifecycle()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    var selectedYear by remember { mutableStateOf(currentYear) }
    val tabs = listOf(
        "Import",
        "Export",
    ) // Danh sách các tab
    var selectedTabIndex by remember { mutableStateOf(0) }
    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        Column(
            modifier = modifier
                .background(colorResource(id = R.color.line_light_gray))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                YearPickerScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 16.dp),
                    currentYear = currentYear,
                    selectedYear = selectedYear,
                    onSelectYear = {
                        selectedYear = it
                        viewModel.getRevenueByYear(it)
                        viewModel.getCostByYear(it)
                    }
                )
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White,
                    contentColor = colorResource(id = R.color.text_color_black),
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                androidx.compose.material.Text(
                                    text = title,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = QuickSand
                                )
                            })
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)

                ) {
                    when (selectedTabIndex) {
                        1 -> {
                            RevenueExport(
                                getRevenueByMonthImport = viewModel::getRevenueByMonthImport,
                                revenueUiState = revenueUiState,
                                revenueByMonthUiState = revenueByMonthUiState,
                                navigateToExportDetail = navigateToExportDetail,
                                selectedYear = selectedYear,
                                scrollBehavior = scrollBehavior,
                            )
                        }

                        0 -> {
                            CostExport(
                                getCostByMonthImport = viewModel::getCostByMonthImport,
                                selectedYear = selectedYear,
                                  costByMonthUiState =costDetailUiState,
                                costUiState = costUiState,
                                  navigateToImportDetail =navigateToImportDetail,
                                scrollBehavior = scrollBehavior,
                            )

                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevenueExport(
    selectedYear: Int,
    getRevenueByMonthImport: (Int, Int) -> Unit,
    revenueUiState: List<MonthlyRevenueResponse>,
    revenueByMonthUiState: List<RevenueByMonthResponse>,
    navigateToExportDetail: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            item {
                if (revenueUiState.isNotEmpty()) {
                    RevenueChart(
                        selectedYear = selectedYear,
                        revenueData = revenueUiState.map { it.convertToUiState() },
                        onSelectMonth = getRevenueByMonthImport,
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Nothing here, it's emty")
                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .alpha(0.5f),
                            painter = painterResource(id = R.drawable.package_image),
                            contentDescription = ""
                        )
                    }
                }
            }
            item {
                Column {
                    revenueByMonthUiState.map {
                        DetailRevenueMonth(
                            onClickToDetail = navigateToExportDetail,
                            revenueByMonthUiState = it,
                        )
                    }
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostExport(
    selectedYear: Int,
    getCostByMonthImport: (Int, Int) -> Unit,
    costUiState: List<MonthlyCostResponse>,
    costByMonthUiState: List<CostByMonthResponse>,
    navigateToImportDetail: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            item {
                if (costUiState.isNotEmpty()) {
                    RevenueChart(
                        selectedYear = selectedYear,
                        revenueData = costUiState.map { it.convertToUiState() },
                        onSelectMonth = getCostByMonthImport,
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Nothing here, it's emty")
                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .alpha(0.5f),
                            painter = painterResource(id = R.drawable.package_image),
                            contentDescription = ""
                        )
                    }
                }
            }
            item {
                Column {
                    costByMonthUiState.map {
                        DetailCostMonth(
                            onClickToDetail = navigateToImportDetail,
                            costByMonthUiState=it
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun DetailRevenueMonth(
    onClickToDetail: (String) -> Unit,
    revenueByMonthUiState: RevenueByMonthResponse,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        modifier = modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClickToDetail(revenueByMonthUiState.id) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.export_package),
                    contentDescription = ""
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    text = revenueByMonthUiState.packageName
                )
            }
            Text(text = revenueByMonthUiState.totalSellingPrice.toString())
        }
    }
}


@Composable
fun DetailCostMonth(
    onClickToDetail: (String) -> Unit,
    costByMonthUiState: CostByMonthResponse,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        modifier = modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClickToDetail(costByMonthUiState.packageId) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.export_package),
                    contentDescription = ""
                )
                Text(
                    fontWeight = FontWeight.Bold,
                    text = costByMonthUiState.packageName
                )
            }
            Text(text = costByMonthUiState.totalImportPrice .toString())
        }
    }
}



@Composable
fun YearPickerScreen(
    selectedYear: Int,
    currentYear: Int,
    onSelectYear: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val years = remember { (1900..currentYear).toList().reversed() }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(text = selectedYear.toString())
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(text = year.toString()) },
                        onClick = {
                            expanded = false
                            onSelectYear(year)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun RevenueChart(
    selectedYear: Int,
    revenueData: List<MonthlyUiState>,
    onSelectMonth: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedMonth by remember { mutableStateOf<Int?>(null) }
    var monthDetail by remember { mutableStateOf<MonthlyRevenueResponse?>(null) }
    val maxRevenue = revenueData.maxOfOrNull { it.total } ?: 0f

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        if (revenueData.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 30.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .drawBehind {
                            val gridColor = Color.LightGray.copy(alpha = 0.5f)
                            val yStep = (size.height - 30.dp.toPx()) / 5
                            for (i in 0..5) {
                                val y = size.height - i * yStep
                                drawLine(
                                    color = gridColor,
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .offset(y = (20).dp)
                            .fillMaxSize()
                            .padding(start = 40.dp), // Để dành chỗ cho trục Y
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        revenueData.forEach { revenue ->
                            val isSelected = selectedMonth == revenue.month
                            val barHeight = if (maxRevenue > 0) {
                                (revenue.total / maxRevenue) * 300.dp.value
                            } else 10f

                            // Animation màu
                            val barColor by animateColorAsState(
                                targetValue = if (isSelected) Color(0xFF2167AA) else Color.Gray,
                                animationSpec = tween(500),
                                label = ""
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(barHeight.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(barColor)
                                        .clickable {
                                            selectedMonth = revenue.month
                                            onSelectMonth(
                                                selectedYear,
                                                revenue.month,
                                            )
                                        }
                                )
                                Text(
                                    text = revenue.month.toString(),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(40.dp)
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        val step = maxRevenue / 5
                        for (i in 5 downTo 0) {
                            Text(
                                text = (step * i).roundToInt().toString(),
                                fontSize = 7.sp,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}
