package com.example.warehousemanagement.ui.feature.customer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.feature.customer.viewModel.AddCustomerViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddOrEditCustomerForm(
    modifier: Modifier = Modifier,
    onSubmit: (CustomerData) -> Unit,
    onBackClick: () -> Unit,
    viewModel: AddCustomerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var customerName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var district by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var postalCode by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = Dimens.PADDING_10_DP),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Customer customerName
            OutlinedTextField(
                value = customerName,
                onValueChange = { customerName = it },
                label = { Text(text = stringResource(id = R.string.customer_name_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Customer email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.email_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Customer address
            OutlinedTextField(
                value = street,
                onValueChange = { street = it },
                label = { Text(text = stringResource(id = R.string.street_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            OutlinedTextField(
                value = district,
                onValueChange = { district = it },
                label = { Text(text = stringResource(id = R.string.district_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(text = stringResource(id = R.string.city_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            OutlinedTextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                label = { Text(text = stringResource(id = R.string.postal_code_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Customer phone number
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = stringResource(id = R.string.phone_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BigButton(
                    modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    enabled = customerName.isNotEmpty() && email.isNotEmpty() && street.isNotEmpty() && district.isNotEmpty() &&
                            city.isNotEmpty() && postalCode.isNotEmpty() && phone.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {
                        viewModel.addNewCustomer(
                            Customer(
                                idCustomer = "",
                                customerName = customerName,
                                email = email,
                                address = Address(
                                    street = street,
                                    district = district,
                                    city = city,
                                    postalCode = postalCode,
                                    phone = phone,
                                ),
                            )
                        )
                        Toast.makeText(context, "added successfully!", Toast.LENGTH_SHORT).show()
                        onBackClick()
                    })
            }
        }
    }
}

data class CustomerData(
    val customerName: String,
    val email: String,
    val street: String,
    val district: String,
    val city: String,
    val postalCode: String,
    val phone: String
)

@Preview(showBackground = true)
@Composable
fun PreviewFormAddOrEditCustomer() {
    FormAddOrEditCustomerForm(
        onSubmit = {},
        onBackClick = {},
    )
}
