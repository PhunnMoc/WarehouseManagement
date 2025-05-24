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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailCustomerUiState
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailCustomerViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCustomerScreen(
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
                var updateCustomer by remember {
                    mutableStateOf(detailCustomer.customer)
                }
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
                        value = updateCustomer.idCustomer,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.customer_id)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Name
                    OutlinedTextField(
                        value = updateCustomer.customerName,
                        enabled = true,
                        onValueChange = { updateCustomer = updateCustomer.copy(customerName = it) },
                        label = { Text(text = stringResource(id = R.string.customer_name_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Email
                    OutlinedTextField(
                        value = updateCustomer.email,
                        enabled = true,
                        onValueChange = { updateCustomer = updateCustomer.copy(email = it) },
                        label = { Text(text = stringResource(id = R.string.email_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Address
                    OutlinedTextField(
                        value = updateCustomer.address.street,
                        enabled = true,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.street_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Customer Phone
                    OutlinedTextField(
                        value = updateCustomer.address.phone,
                        onValueChange = {},
                        enabled = true,
                        label = { Text(text = stringResource(id = R.string.phone_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    // Customer City
                    OutlinedTextField(
                        value = updateCustomer.address.city,
                        onValueChange = {},
                        enabled = true,
                        label = { Text(text = stringResource(id = R.string.city_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    // Customer Postal Code
                    OutlinedTextField(
                        value = updateCustomer.address.postalCode,
                        onValueChange = {},
                        enabled = true,
                        label = { Text(text = stringResource(id = R.string.postal_code_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BigButton(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true,
                        labelname = "Update",
                        onClick = {
                            viewModel.updateCustomer(customer = updateCustomer)
                            onBackClick()
                        })
                }
            }

            else -> {}
        }
    }
}
