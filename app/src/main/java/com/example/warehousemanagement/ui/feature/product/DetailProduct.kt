package com.example.warehousemanagement.ui.feature.product

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.example.warehousemanagement.ui.feature.importPackage.UploadImageButton
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailProductUiState
import com.example.warehousemanagement.ui.feature.product.viewModel.DetailProductViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProduct(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailProductViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val detailProductUiState by viewModel.detailProductUiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            HeaderOfScreen(
                mainTitleText = stringResource(id = R.string.screen_product_main_title),
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
        when (val detailproduct = detailProductUiState) {
            is DetailProductUiState.Loading -> IndeterminateCircularIndicator()
            is DetailProductUiState.Error -> NothingText()
            is DetailProductUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.PADDING_10_DP),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    OutlinedTextField(
                        value = detailproduct.product.id,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.product_id)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )
                    //Product customerName
                    OutlinedTextField(
                        value = detailproduct.product.productName,
                        enabled = false,
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.product_name_string)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    //Genre
                    OutlinedTextField(
                        value = detailproduct.product.genre?.genreName ?: "",
                        onValueChange = {},
                        label = { Text("Genre") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    //Supplier
                    OutlinedTextField(
                        value = detailproduct.product.supplier?.name ?: "",
                        onValueChange = {},
                        label = { Text("Supplier") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    //Quantity
                    OutlinedTextField(
                        value = detailproduct.product.quantity.toString(),
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.quantity_title)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    //Storage Location
                    OutlinedTextField(
                        value = detailproduct.product.storageLocation?.storageLocationName ?: "",
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = "Storage Location") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    //importPrice
                    OutlinedTextField(
                        value = detailproduct.product.importPrice.toString(),
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.import_price_title)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    //sellingPrice
                    OutlinedTextField(
                        value = detailproduct.product.sellingPrice.toString(),
                        onValueChange = {},
                        enabled = false,
                        label = { Text(text = stringResource(id = R.string.selling_price_title)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    // DateTime Picker
                    OutlinedTextField(
                        value = detailproduct.product.lastUpdated.toString(),
                        onValueChange = {},
                        enabled = false,
                        label = { Text("Date") },
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.LightGray,
                            unfocusedBorderColor = Color.Gray,
                            textColor = Color.Gray,
                        ),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    //image
                    UploadImageButton(
                        inputImageUrl = detailproduct.product.image,
                        onImageSelected = {})

                    //  "inStock": true
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = detailproduct.product.inStock,
                            onCheckedChange = { },
                            enabled = false
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("In stock")
                    }


                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(Dimens.PADDING_10_DP),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

//                        BigButton(modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
//                            enabled = customerName.isNotEmpty() && selectedOption.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty(),
//                            labelname = "Submit",
//                            onClick = {
//                                onSubmit(
//                                    FormData(
//                                        customerName = customerName,
//                                        selectedOption = selectedOption,
//                                        number = number,
//                                        date = date,
//                                        isChecked = isChecked,
//                                        fileName = fileName
//                                    )
//                                )
//                            })
//                        IconButton(
//                            enabled = customerName.isNotEmpty() && selectedOption.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty(),
//                            onClick = { onAdd1MoreProduct() }) {
//                            Icon(
//                                modifier = Modifier.size(Dimens.SIZE_ICON_35_DP),
//                                tint = colorResource(id = R.color.background_theme),
//                                painter = painterResource(id = R.drawable.ic_add_mini_button),
//                                contentDescription = ""
//                            )
//                        }

                    }
                }
            }
        }
    }
}