package com.example.warehousemanagement.ui.feature.report.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.CustomIconButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.theme.QuickSand
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreByRangeDay(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var startRangeDate by remember { mutableStateOf<Long?>(null) }
    var endRangeDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            LazyRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                item {
                    CustomIconButton(
                        text = "7 days",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.width(5.dp)) // Space between buttons

                }
                item {
                    CustomIconButton(
                        text = "Custom",
                        icon = painterResource(id = R.drawable.baseline_menu_24),
                        onClick = { showModal = true }
                    )
                }
            }

            if (startRangeDate != null && endRangeDate != null) {
                Text(
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    fontFamily = QuickSand,
                    text = "${startRangeDate?.convertMillisToDate()} _ ${endRangeDate?.convertMillisToDate()}"
                )
            }

        }
        if (showModal) {
            DateRangePickerModal(
                onDateRangeSelected = {
                    startRangeDate = it.first
                    endRangeDate = it.second
                },
                onDismiss = { showModal = false },
            )
        }
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
    DatePickerDialog(
        colors = DatePickerDefaults.colors(
            containerColor = Color.White,
        ),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            modifier = Modifier
                .padding(10.dp)
                .height(500.dp),
            state = dateRangePickerState,
            title = {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Select date range"
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

@Preview
@Composable
fun PreviewGenreByRangeDay() {
    //  DateRangePickerModal(onDateRangeSelected = {}, onDismiss = {})
    GenreByRangeDay(
        onBack = {},
    )
}