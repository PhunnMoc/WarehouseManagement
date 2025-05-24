package com.example.warehousemanagement.ui.feature.report.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.network.dto.GenreByRangeSummaryResponse
import com.example.warehousemanagement.ui.common.CustomIconButton
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChart
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartData
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartDataCollection
import com.example.warehousemanagement.ui.feature.report.donutChart.getRandomColor
import com.example.warehousemanagement.ui.feature.report.donutChart.toMoneyFormat
import com.example.warehousemanagement.ui.feature.report.list.CardGenre
import com.example.warehousemanagement.ui.feature.report.viewModel.GenreByRangeViewModel
import com.example.warehousemanagement.ui.theme.QuickSand
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreByRangeDay(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GenreByRangeViewModel = hiltViewModel(),
    scrollBehavior: TopAppBarScrollBehavior,
) {

    val rangeUiState by viewModel.rangeUiState.collectAsStateWithLifecycle()
    val genreByRangeSummaryImportUiState by viewModel.genreByRangeSummaryImportResponse.collectAsStateWithLifecycle()
    val genreByRangeSummaryExportUiState by viewModel.genreByRangeSummaryExportResponse.collectAsStateWithLifecycle()
    var showModal by remember { mutableStateOf(false) }

    val tabs = listOf(
        "Import",
        "Export",
    ) // Danh sách các tab
    var selectedTabIndex by remember { mutableStateOf(0) }
    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        Column(
            modifier = modifier
        ) {
            LazyRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                item {
                    CustomIconButton(text = "7 days", onClick = {})
                    Spacer(modifier = Modifier.width(5.dp)) // Space between buttons
                }
                item {
                    CustomIconButton(text = "Custom",
                        icon = painterResource(id = R.drawable.baseline_menu_24),
                        onClick = { showModal = true })
                }
            }
            Text(
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                fontFamily = QuickSand,
                text = "From ${rangeUiState.startRangeDate.convertMillisToDate()} to ${rangeUiState.endRangeDate.convertMillisToDate()}"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.line_light_gray))
            ) {
                // TabRow để hiển thị các tab
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
                        0 -> {
                            TabImport(
                                scrollBehavior = scrollBehavior,
                                listGenre = genreByRangeSummaryImportUiState,
                            )
                        }

                        1 -> {
                            TabImport(
                                scrollBehavior = scrollBehavior,
                                listGenre = genreByRangeSummaryExportUiState,
                            )
                        }
                    }
                }
            }
        }
    }
    if (showModal) {
        DateRangePickerModal(
            onDateRangeSelected = {
                viewModel.updateGenreByRange(
                    startRangeDate = it.first,
                    endRangeDate = it.second,
                )
                viewModel.getGenreByRangeExport()
                viewModel.getGenreByRangeImport()
            },

            onDismiss = { showModal = false },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabImport(
    listGenre: List<GenreByRangeSummaryResponse>,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    if (listGenre.isNotEmpty()) {
        ContentGenreByRangeDay(
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            listGenre = listGenre,
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
                painter = painterResource(id = R.drawable.package_image), contentDescription = ""
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentGenreByRangeDay(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    listGenre: List<GenreByRangeSummaryResponse>,
) {
    val viewData = DonutChartDataCollection(listGenre.map { genreSummary ->
        DonutChartData(
            amount = genreSummary.total.toFloat(),
            color = getRandomColor(),
            title = genreSummary.genreName,
        )
    })
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Column {

                    DonutChart(data = viewData) { selected ->
                        AnimatedContent(targetState = selected, label = "") {
                            val amount = it?.amount ?: viewData.totalAmount
                            val text = it?.title ?: "Total"

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                androidx.compose.material.Text(text = "$${amount.toMoneyFormat(true)}")
                                androidx.compose.material.Text(text = text, fontFamily = QuickSand)
                            }
                        }
                    }
                    viewData.items.map {
                        val progress = it.amount / viewData.totalAmount
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                modifier = Modifier.width(150.dp)
                            ) {
                                Text(
                                    fontSize = 10.sp, color = it.color, text = String.format(
                                        "%.1f",
                                        progress * 100,
                                    ) // Định dạng với 2 chữ số sau dấu phẩy
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp),
                                    fontSize = 10.sp,
                                    text = it.title
                                )
                            }
                            ProgressBar(
                                progress = progress,
                                modifier = Modifier.fillMaxWidth(),
                                height = 10.dp,
                                progressColor = it.color
                            )
                        }

                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }

        itemsIndexed(listGenre) { index, it ->
            CardGenre(
                modifier = Modifier.padding(vertical = 10.dp),
                genreName = it.genreName,
                moneyInStock = it.total.toFloat(),
                id = it.genreId,
            )
        }
    }
}

@Composable
fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier, height: Dp = 10.dp,
    backgroundColor: Color = Color(0xFFE8E8E8),
    progressColor: Color = Color.Blue
) {
    var parentWidth by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .height(height)
            .background(color = backgroundColor)
            .onGloballyPositioned { coordinates ->
                parentWidth = coordinates.size.width
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(with(LocalDensity.current) { (parentWidth * progress).toDp() }) // Chuyển từ pixel sang dp
                .background(color = progressColor)
        )
    }
}

fun Long.convertMillisToDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(this))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dateRangePickerState = rememberDateRangePickerState()
    DatePickerDialog(colors = DatePickerDefaults.colors(
        containerColor = Color.White,
    ), onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = {
            onDateRangeSelected(
                Pair(
                    dateRangePickerState.selectedStartDateMillis,
                    dateRangePickerState.selectedEndDateMillis
                )
            )
            onDismiss()
        }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel")
        }
    }) {
        DateRangePicker(
            modifier = Modifier
                .padding(10.dp)
                .height(500.dp),
            state = dateRangePickerState,
            title = {
                Text(
                    modifier = Modifier.padding(10.dp), text = "Select date range"
                )
            },
            showModeToggle = true,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                headlineContentColor = Color.Black,
                weekdayContentColor = Color.Black,
                subheadContentColor = Color.Black,
                yearContentColor = Color.Black,
                currentYearContentColor = Color.Red,
                selectedYearContainerColor = Color.Red,
                disabledDayContentColor = Color.Gray,
                todayDateBorderColor = Color.Black,
                dayInSelectionRangeContainerColor = Color.LightGray,
                dayInSelectionRangeContentColor = Color.White,
                selectedDayContainerColor = Color.Black
            )
        )


    }
}
