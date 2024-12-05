package com.example.warehousemanagement.ui.feature.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.common.SearchBarPreview
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon
import com.example.warehousemanagement.ui.feature.home.HalfIcon
import com.example.warehousemanagement.ui.feature.product.viewModel.ProductUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(
                scrollBehavior = scrollBehavior,
                mainTitleText = stringResource(id = R.string.screen_report_main_title),
                endContent = {})
        }) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            IncomeOutcome()
        }
    }
}

@Composable
fun IncomeOutcome() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFD699), Color(0xFFFFF8CD)) // Gradient colors
                ),
                shape = RoundedCornerShape(10.dp) // Rounded corners
            )
            .padding(10.dp)
    ) {
        Text(
            text = "Income Outcome",
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
                    .padding(Dimens.PADDING_5_DP)
                    .weight(1f)
                    .height(80.dp)
                    .background(
                        color = Color(0xC4FFFAEB),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    Icon(painter = painterResource(id = R.drawable.import_package), contentDescription ="" )
                    Text(
                        text = "\$500",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(text = "Total", fontFamily = QuickSand)
            }
            Column(
                modifier = Modifier
                    .padding(Dimens.PADDING_5_DP)
                    .weight(1f)
                    .height(80.dp)
                    .background(
                        color = Color(0xC4FFFAEB),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    Icon(painter = painterResource(id = R.drawable.export_package), contentDescription ="" )
                    Text(
                        text = "\$500",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(text = "Total", fontFamily = QuickSand)
            }
        }
    }
}

@Preview
@Composable
fun PreviewIncomeOutcome() {
    IncomeOutcome()
}