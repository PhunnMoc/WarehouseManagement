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
                modifier = modifier.padding(
                    top = Dimens.PADDING_20_DP,
                    start = Dimens.PADDING_20_DP,
                    end = Dimens.PADDING_20_DP,
                    bottom = Dimens.PADDING_10_DP
                ),
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
                        value = detailproduct.product.idProduct,
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
//                    var expandedGenre by remember { mutableStateOf(false) }
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentSize(Alignment.TopStart)
//                    ) {
//                        OutlinedTextField(
//                            value = selectedOption,
//                            onValueChange = {},
//                            label = { Text("Genre") },
//                            modifier = Modifier.fillMaxWidth(),
//                            readOnly = true,
//                            shape = RoundedCornerShape(Dimens.PADDING_10_DP)
//                        )
//                        IconButton(modifier = Modifier
//                            .zIndex(1f)
//                            .align(Alignment.BottomEnd),
//                            onClick = { expandedGenre = true }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_add_mini_button),
//                                contentDescription = ""
//                            )
//                        }
//                        DropdownMenu(
//                            expanded = expandedGenre,
//                            onDismissRequest = { expandedGenre = false },
//                            modifier = Modifier
//                                .padding(horizontal = Dimens.PADDING_10_DP)
//                                .fillMaxWidth()
//
//                        ) {
//                            options.forEach { option -> //TODO()
//                                DropdownMenuItem(onClick = {
//                                    selectedOption = option
//                                    expandedGenre = false
//                                }) {
//                                    Text(option)
//                                }
//                            }
//                            Text(text = "Add new Genre")
//                        }
//                    }

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

                    //supplier
//                    var expandedSupplier by remember { mutableStateOf(false) }
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentSize(Alignment.TopStart)
//                    ) {
//                        OutlinedTextField(
//                            value = selectedOption,
//                            onValueChange = {},
//                            label = { Text("Genre") },
//                            modifier = Modifier.fillMaxWidth(),
//                            readOnly = true,
//                            shape = RoundedCornerShape(Dimens.PADDING_10_DP)
//                        )
//                        IconButton(modifier = Modifier
//                            .zIndex(1f)
//                            .align(Alignment.BottomEnd),
//                            onClick = { expandedSupplier = true }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_add_mini_button),
//                                contentDescription = ""
//                            )
//                        }
//                        DropdownMenu(
//                            expanded = expandedSupplier,
//                            onDismissRequest = { expandedSupplier = false },
//                            modifier = Modifier
//                                .padding(horizontal = Dimens.PADDING_10_DP)
//                                .fillMaxWidth()
//                        ) {
//                            options.forEach { option -> //TODO()
//                                DropdownMenuItem(onClick = {
//                                    selectedOption = option
//                                    expandedSupplier = false
//                                }) {
//                                    Text(option)
//                                }
//                            }
//                            Text(text = "Add new Supplier")
//                        }
//                    }

                    //storageLocationId
//                    var expandedStorageLocation by remember { mutableStateOf(false) }
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentSize(Alignment.TopStart)
//                    ) {
//                        OutlinedTextField(
//                            value = selectedOption,
//                            onValueChange = {},
//                            label = { Text("Genre") },
//                            modifier = Modifier.fillMaxWidth(),
//                            readOnly = true,
//                            shape = RoundedCornerShape(Dimens.PADDING_10_DP)
//                        )
//                        IconButton(modifier = Modifier
//                            .zIndex(1f)
//                            .align(Alignment.BottomEnd),
//                            onClick = { expandedStorageLocation = true }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_add_mini_button),
//                                contentDescription = ""
//                            )
//                        }
//                        DropdownMenu(
//                            expanded = expandedStorageLocation,
//                            onDismissRequest = { expandedStorageLocation = false },
//                            modifier = Modifier
//                                .padding(horizontal = Dimens.PADDING_10_DP)
//                                .fillMaxWidth()
//                        ) {
//                            options.forEach { option -> //TODO()
//                                DropdownMenuItem(onClick = {
//                                    selectedOption = option
//                                    expandedStorageLocation = false
//                                }) {
//                                    Text(option)
//                                }
//                            }
//                        }
//                    }


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
                    UploadImageButton(onImageSelected = {})

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