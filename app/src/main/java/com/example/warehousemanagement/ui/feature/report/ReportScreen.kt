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

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.warehousemanagement.ui.feature.report.lineChart.PerformanceChart
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HeaderOfScreen(
                mainTitleText = "Report",
                startContent = {
                    IconButton(onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_menu_24), // Replace with your menu icon resource
                            contentDescription = "Menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        drawerContent = {
            DrawerContent(navController = navController, scaffoldState = scaffoldState)
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController, startDestination = "screen_a"
        ) {
            composable("screen_a") { DashBoardReportScreen() }
            composable("screen_b") { InventoryScreen() }
            composable("screen_c") { StorageLocationReportScreen() }
        }
    }
}

@Composable
fun DrawerContent(navController: NavController, scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("Report", style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
        Divider()

        DrawerOption("DashBoard", "screen_a", navController, scaffoldState)
        DrawerOption("Inventory", "screen_b", navController, scaffoldState)
        DrawerOption("Statistics by month", "screen_c", navController, scaffoldState)
    }
}

@Composable
fun DrawerOption(
    label: String,
    route: String,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()

    TextButton(onClick = {
        coroutineScope.launch {
            scaffoldState.drawerState.close()
        }
        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }) {
        Text(label)
    }
}

@Composable
fun ScreenContent(screenName: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = screenName, style = MaterialTheme.typography.h4)
    }
}

@Preview
@Composable
fun PreviewIncomeOutcome() {
    // IncomeOutcome()
    //  AppScaffold()
}