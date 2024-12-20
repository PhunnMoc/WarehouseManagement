package com.example.warehousemanagement.ui.feature.exportPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.InputArea
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.FormAddExportedProductViewModel
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchGenreUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import com.example.warehousemanagement.ui.theme.Dimens
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FormAddExportedProduct(
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FormAddExportedProductViewModel = hiltViewModel()
) {

    var productName by remember { mutableStateOf("") }
    var product by remember { mutableStateOf<Product?>(null) }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "") }

    val listProduct by viewModel.searchProductUiState.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier,
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
                                //  onNavigationToHome()
                            })
                },
                endContent = {},
                scrollBehavior = null
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
            //Product
            var expandedProduct by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = productName,
                onValueChange = {
                    productName = it
                    viewModel.onChangeSearchProductQuery(it)
                    expandedProduct = true
                },
                label = { Text("Product") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            AnimatedVisibility(
                visible = expandedProduct,
                enter = expandVertically(animationSpec = tween(durationMillis = 500)) + fadeIn(),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 500)) + fadeOut()
            ) {
                when (val products = listProduct) {
                    is SearchProductUiState.Loading -> IndeterminateCircularIndicator()
                    is SearchProductUiState.Error -> NothingText()
                    is SearchProductUiState.Success -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.PADDING_10_DP)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(Dimens.PADDING_10_DP)
                                )
                                .padding(Dimens.PADDING_5_DP)
                        ) {
                            if (products.listSuggestionProduct.isEmpty()) {
                                Text(
                                    "No Product found",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(Dimens.PADDING_5_DP)
                                )
                            } else {
                                products.listSuggestionProduct.map { option ->
                                    ProductCard(
                                        product = option,
                                        onCardClick = {
                                            productName = option.productName
                                            product = option
                                            expandedProduct = false
                                        },
                                        onLongPress = {
                                            productName = option.productName
                                            product = option
                                            expandedProduct = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            OutlinedTextField(
                value = quantity,
                onValueChange = { if (it.all { char -> char.isDigit() }) quantity = it },
                label = { Text(text = stringResource(id = R.string.quantity_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            val date by remember {
                mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            }
            // DateTime Picker
            OutlinedTextField(
                value = date,
                onValueChange = {},
                label = { Text("Date") },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Gray,
                ),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                BigButton(modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    enabled = productName.isNotEmpty() && product != null
                            && quantity.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {
                        product?.idProduct?.let {
                            viewModel.updateProduct(
                                productId = it,
                                quantity = quantity,
                            )
                        }
                        viewModel.addPackage()
                        onNavigationBack()
                    })
                IconButton(enabled = productName.isNotEmpty() && product != null
                        && quantity.isNotEmpty(),
                    onClick = {
                        product?.idProduct?.let {
                            viewModel.updateProduct(
                                productId = it,
                                quantity = quantity,
                            )
                        }
                        productName = ""
                        product = null
                        quantity = ""
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(Dimens.SIZE_ICON_35_DP),
                        tint = colorResource(id = R.color.background_theme),
                        painter = painterResource(id = R.drawable.ic_add_mini_button),
                        contentDescription = ""
                    )
                }
            }
        }

    }
}
