package com.example.warehousemanagement.ui.feature.supplier

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.ShowText
import com.example.warehousemanagement.ui.feature.supplier.viewModel.AddSupplierViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddOrEditSupplierForm(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: AddSupplierViewModel = hiltViewModel()
) {
    //   val result by viewModel.result
    val context = LocalContext.current


    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var district by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var postalCode by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var ratings by rememberSaveable { mutableStateOf(0) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            HeaderOfScreen(
                modifier = modifier,
                mainTitleText = stringResource(id = R.string.screen_supplier_main_title),
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
            // Supplier customerName
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.supplier_name_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Supplier email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.email_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Supplier address
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

            // Supplier phone number
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = stringResource(id = R.string.phone_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            // Supplier ratings
            OutlinedTextField(
                value = ratings.toString(),
                onValueChange = { ratings = it.toInt() },
                label = { Text(text = stringResource(id = R.string.supplier_ratings_string)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
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
                    enabled = name.isNotEmpty() && email.isNotEmpty() && street.isNotEmpty() && district.isNotEmpty() &&
                            city.isNotEmpty() && postalCode.isNotEmpty() && phone.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {
                        viewModel.addNewSupplier(
                            Supplier(
                                idSupplier = "",
                                name = name,
                                email = email,
                                address = Address(
                                    street = street,
                                    district = district,
                                    city = city,
                                    postalCode = postalCode,
                                    phone = phone,
                                ),
                                ratings = ratings,
                            )
                        )
                        onBackClick( )
                        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFormAddOrEditSupplier() {
    FormAddOrEditSupplierForm(
        onBackClick = {},
    )
}
