package com.example.warehousemanagement.ui.feature.customer

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.CustomerCard
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.SearchBarPreview
import com.example.warehousemanagement.ui.feature.customer.viewModel.CustomerUIState
import com.example.warehousemanagement.ui.feature.customer.viewModel.CustomerViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(
    modifier: Modifier = Modifier,
    onClickAddCustomer: () -> Unit,
    onBackClick: () -> Unit,
    onNavigationDetailCustomer: (String) -> Unit,
    onNavigateToEditCustomerScreen: (String) -> Unit,
    onClickSearch: () -> Unit,
    viewModel: CustomerViewModel = hiltViewModel()
) {
    val roleUiState by viewModel.roleUiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val customerUIState by viewModel.customerUIState.collectAsStateWithLifecycle()
    var isExpanded by remember { mutableStateOf(false) }
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            if (roleUiState) {
                Box(
                    contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxSize()
                ) {
                    if (isExpanded) {
                        Box(modifier = Modifier
                            .offset(x = 16.dp, y = 15.dp)
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable { isExpanded = false })
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        AnimatedVisibility(visible = isExpanded) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp, end = 16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            Color.White, shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(text = "Add customer", color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                FloatingActionButton(
                                    onClick = { onClickAddCustomer() },
                                    containerColor = colorResource(id = R.color.background_gray)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Action 1")
                                }
                            }
                        }

                        FloatingActionButton(
                            modifier = Modifier.padding(bottom = 8.dp, end = 16.dp),
                            onClick = { isExpanded = !isExpanded },
                            containerColor = colorResource(id = R.color.background_theme)
                        ) {
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.Menu else Icons.Default.Add,
                                contentDescription = "Toggle FAB"
                            )
                        }
                    }
                }
            }
        },
        topBar = {
            HeaderOfScreen(
                mainTitleText = stringResource(id = R.string.screen_customer_main_title),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackClick()
                            })
                },
                endContent = {},
                scrollBehavior = scrollBehavior
            )
        }) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Dimens.PADDING_16_DP),
            ) {
                SearchBarPreview(
                    enabled = false,
                    modifier = Modifier.clickable {
                        onClickSearch()
                    }
                )
            }

            //Spacer(modifier = Modifier.height(16.dp))

            when (val customer = customerUIState) {
                is CustomerUIState.Loading -> IndeterminateCircularIndicator()
                is CustomerUIState.Error -> NothingText()
                is CustomerUIState.Success -> {
                    LazyColumn(modifier = Modifier.padding(Dimens.PADDING_10_DP)) {
                        items(customer.listCustomer) { customer ->
                            CustomerCard(
                                customer = customer,
                                onCardClick = {},
                                onLongPress = onNavigationDetailCustomer,
                                roleUiState=roleUiState,
                                onEditCustomer= onNavigateToEditCustomerScreen,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

        }
    }

}


//@Preview(showBackground = true)
//@Composable
//fun PreviewCustomerScreen() {
//    WarehouseManagementTheme {
//        //  ProductsScreen(products = listProduct)
//    }
//}