package com.example.warehousemanagement.ui.feature.report

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.HeaderOfScreen

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.warehousemanagement.ui.feature.report.ui.GenreByRangeDay
import com.example.warehousemanagement.ui.feature.report.ui.RevenueCostScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    navigateToExportDetail: (String) -> Unit,
    navigateToImportDetail: (String) -> Unit,
) {
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
            composable("screen_a") { DashBoardReportScreen(scrollBehavior = scrollBehavior) }
            composable("screen_b") {
                RevenueCostScreen(
                    scrollBehavior = scrollBehavior,
                    navigateToExportDetail = navigateToExportDetail,
                    navigateToImportDetail = navigateToImportDetail,
                )
            }
            composable("screen_c") {
                GenreByRangeDay(
                    scrollBehavior = scrollBehavior,
                    onBack = {}
                )
            }
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