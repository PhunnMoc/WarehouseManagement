package com.example.warehousemanagement.ui.feature.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailCustomerUiState
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailCustomerViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCustomer(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailCustomerViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val detailCustomerUiState by viewModel.detailCustomerUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            HeaderOfScreen(
                modifier = modifier.padding(
                    top = Dimens.PADDING_20_DP,
                    start = Dimens.PADDING_20_DP,
                    end = Dimens.PADDING_20_DP,
                    bottom = Dimens.PADDING_10_DP
                ),
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
        }) { innerPadding ->

        when (val detailCustomer = detailCustomerUiState) {
            is DetailCustomerUiState.Loading -> IndeterminateCircularIndicator()
            is DetailCustomerUiState.Error -> NothingText()
            is DetailCustomerUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.PADDING_10_DP),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Customer ID
                    OutlinedTextField(
                        value = detailCustomer.customer.idCustomer,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.customer_id)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Name
                    OutlinedTextField(
                        value = detailCustomer.customer.customerName,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.customer_name_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Email
                    OutlinedTextField(
                        value = detailCustomer.customer.email,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.email_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Address
                    OutlinedTextField(
                        value = detailCustomer.customer.address.street,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.street_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Phone
                    OutlinedTextField(
                        value = detailCustomer.customer.address.phone,
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.phone_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    // Customer City
                    OutlinedTextField(
                        value = detailCustomer.customer.address.city,
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.city_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    // Customer Postal Code
                    OutlinedTextField(
                        value = detailCustomer.customer.address.postalCode,
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.postal_code_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            else -> {}
        }
    }
}
