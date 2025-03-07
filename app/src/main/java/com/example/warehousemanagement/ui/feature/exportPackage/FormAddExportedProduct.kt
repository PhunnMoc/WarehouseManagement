package com.example.warehousemanagement.ui.feature.exportPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.network.dto.ExportProductDto
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.InputArea
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.feature.customer.viewModel.CustomerViewModel
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.FormAddExportedProductViewModel
import com.example.warehousemanagement.ui.feature.importPackage.TotalImportPackage
import com.example.warehousemanagement.ui.feature.search.CustomerItem
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerViewModel
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.customTextFieldColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FormAddExportedProduct(
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FormAddExportedProductViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var shouldEnableSubmitButton by remember { mutableStateOf(false) }

    var packageName by remember { mutableStateOf("") }
    var packageDescription by remember { mutableStateOf("") }
    var customer by remember { mutableStateOf<Customer?>(null) }
    var exportProductList by remember { mutableStateOf(mutableListOf(FormExportProductData())) }

    val listProduct by viewModel.searchProductUiState.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = colorResource(id = R.color.line_light_gray),
        topBar = {
            HeaderOfScreen(
                containerColor = Color.Transparent,
                mainTitleText = stringResource(id = R.string.screen_import_main_title),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onNavigationBack()
                            })
                },
                endContent = {},
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.line_light_gray))
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BigButton(modifier = Modifier.fillMaxWidth(),
                    enabled = shouldEnableSubmitButton,
                    labelname = "Submit",
                    onClick = {
                        viewModel.addPackage(
                            listExportProducts = exportProductList.map { it.toExportProductDto() },
                            note = packageDescription,
                            packageName = packageName,
                            date = LocalDate.now().atStartOfDay()
                                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                            customerId = customer?.idCustomer ?: "hehe"
                        )
                        onNavigationBack()
                    })
            }
        }
    ) { innerPadding ->
        CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
            FormExportProducts(
                modifier = Modifier.padding(innerPadding),
                upDateStatusSubmitButton = {
                    shouldEnableSubmitButton = it
                },
                onRemove = { index ->
                    exportProductList = exportProductList.toMutableList().apply { removeAt(index) }
                },
                onExportProductChange = { updatedProduct, index ->
                    exportProductList =
                        exportProductList.toMutableList().apply { set(index, updatedProduct) }
                },
                exportProductList = exportProductList,
                onAddOneMoreExportProduct = {
                    exportProductList =
                        exportProductList.toMutableList().apply { add(FormExportProductData()) }
                },
                packageName = packageName,
                packageDescription = packageDescription,
                onUpdatePackageName = {
                    packageName = it
                },
                onUpdatePackageDescription = {
                    packageDescription = it
                },
                customer = customer,
                onUpdateCustomer = {
                    customer = it
                },
            )
        }
    }
}

@Composable
fun FormExportProducts(
    exportProductList: List<FormExportProductData>,
    modifier: Modifier = Modifier,
    viewModel: FormAddExportedProductViewModel = hiltViewModel(),
    upDateStatusSubmitButton: (Boolean) -> Unit,
    onRemove: (Int) -> Unit,
    onExportProductChange: (FormExportProductData, Int) -> Unit,
    onAddOneMoreExportProduct: () -> Unit,
    packageName: String,
    packageDescription: String,
    onUpdatePackageName: (String) -> Unit,
    onUpdatePackageDescription: (String) -> Unit,
    customer: Customer?,
    onUpdateCustomer: (Customer) -> Unit ,
) {
    val searchProduct by viewModel.searchProductUiState.collectAsStateWithLifecycle()
    val isAllFull = exportProductList.all { it.isFill() }

    LaunchedEffect(isAllFull) {
        upDateStatusSubmitButton(isAllFull)
    }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        item {
            CardInfoExportPackage(
                packageName = packageName,
                packageDescription = packageDescription,
                onUpdatePackageName = onUpdatePackageName,
                onUpdatePackageDescription = onUpdatePackageDescription,
                customer = customer,
                onUpdateCustomer = onUpdateCustomer,
            )
        }
        item {
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White),
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                exportProductList.mapIndexed { index, item ->
                    FormExportProduct(
                        exportProduct = item,
                        index = (index + 1).toString(),
                        onRemove = {
                            onRemove(index)
                        },
                        listProduct = searchProduct,
                        onChangeSearchProductQuery = viewModel::onChangeSearchProductQuery,
                        onExportProductChange = { updatedProduct ->
                            onExportProductChange(updatedProduct, index)
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }

                BigButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    enabled = isAllFull,
                    onClick = onAddOneMoreExportProduct,
                    labelname = "+ Add new product"
                )
                TotalImportPackage()
            }
        }
    }
}

@Composable
fun CardInfoExportPackage(
    customer: Customer?,
    packageName: String,
    packageDescription: String,
    onUpdateCustomer: (Customer) -> Unit = {},
    onUpdatePackageName: (String) -> Unit = {},
    onUpdatePackageDescription: (String) -> Unit = {},
) {
    var shouldShowBottomSheet by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 15.dp),
    ) {
        val date by remember {
            mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        }
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = "Package's Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        OutlinedTextField(
            value = packageName,
            onValueChange = { onUpdatePackageName(it) },
            label = {
                Text(
                    fontFamily = QuickSand,
                    text = stringResource(id = R.string.package_name_title)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            colors = customTextFieldColors(),
        )
        InputArea(
            label = "Description",
            value = packageDescription,
            onValueChange = { onUpdatePackageDescription(it) }
        )
        OutlinedTextField(
            value = date,
            onValueChange = {},
            label = {
                Text(
                    fontFamily = QuickSand,
                    text = "Date"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true,
            colors = customTextFieldColors(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
        )
        OutlinedTextField(
            value = customer?.customerName ?: "",
            onValueChange = {
            },
            label = {
                Text(
                    fontFamily = QuickSand,
                    text = "Date"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true,
            colors = customTextFieldColors(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            trailingIcon = {
                IconButton(onClick = { shouldShowBottomSheet = true }) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.down_arrow_svgrepo_com),
                        contentDescription = ""
                    )
                }
            }

        )
//        BigButton(
//            modifier = Modifier.padding(vertical = 10.dp),
//            labelname = "Choose customer",
//            enabled = true,
//            onClick = { shouldShowBottomSheet = true },
//        )
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = "Create an import package and another employee will check it before it is officially put into the warehouse.",
            fontSize = 10.sp,
        )
        if (shouldShowBottomSheet) {
            ChooseCustomerIdBottomSheet(
                currentId = "",
                onClickCustomer = { onUpdateCustomer(it) },
                onDismiss = { shouldShowBottomSheet = false }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FormExportProduct(
    index: String = "0",
    listProduct: SearchProductUiState,
    onRemove: () -> Unit,
    onChangeSearchProductQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
    onExportProductChange: (FormExportProductData) -> Unit,
    exportProduct: FormExportProductData = FormExportProductData(),
) {
    val exportProductData = remember { exportProduct }
    Column(
        modifier = modifier
            .fillMaxSize()
            //  .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp, horizontal = 15.dp),
    ) {
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = "List of new products (You can add new Genres and new Suppliers)",
            fontSize = 10.sp,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_importpackage),
                    contentDescription = "Product"
                )
                Text(
                    fontSize = 18.sp,
                    text = "Product $index",
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.W900,
                )
            }
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onRemove() },
                painter = painterResource(id = R.drawable.trash_bin),
                contentDescription = "Delete"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.PADDING_10_DP)
        ) {

            // Quantity (30%)
            OutlinedTextField(
                value = exportProductData.quantity,
                maxLines = 1,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        exportProductData.quantity = it
                        onExportProductChange(exportProductData.copy(quantity = it))
                    }
                },
                label = {
                    Text(
                        fontFamily = QuickSand,
                        text = stringResource(id = R.string.quantity_title)
                    )
                },
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                colors = customTextFieldColors(),
            )
        }
        var expandedProduct by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = exportProductData.productName,
            onValueChange = {
                exportProductData.productName = it
                onExportProductChange(exportProductData.copy(productName = it))
                onChangeSearchProductQuery(it)
                expandedProduct = true
            },
            label = { Text("Product") },
            colors = customTextFieldColors(),
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
                                        exportProductData.productName = option.productName
                                        exportProductData.product = option
                                        onExportProductChange(
                                            exportProductData.copy(
                                                product = option,
                                                productName = option.productName
                                            )
                                        )
                                        expandedProduct = false
                                    },
                                    onLongPress = {
//                                            exportProductData.productName = option.productName
//                                            exportProductData.product = option
//                                            expandedProduct = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

class FormExportProductData() {
    var productName by mutableStateOf("")
    var product by mutableStateOf<Product?>(null)
    var quantity by mutableStateOf("")

    // Tạo một bản sao mới
    fun copy(
        product: Product? = this.product,
        productName: String = this.productName,
        quantity: String = this.quantity,
    ): FormExportProductData {
        return FormExportProductData().apply {
            this.product = product
            this.productName = productName
            this.quantity = quantity
        }
    }

    fun toExportProductDto(): ExportProductDto {
        return ExportProductDto(
            productId = product?.idProduct ?: "",
            quantity = quantity.toInt(),
        )
    }

    fun isFill(): Boolean {
        return productName.isNotEmpty() &&
                product != null &&
                quantity.isNotEmpty()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseCustomerIdBottomSheet(
    currentId: String,
    onClickCustomer: (Customer) -> Unit,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModelCustomer: SearchCustomerViewModel = hiltViewModel()
) {
    var searchCustomer by remember { mutableStateOf("") }
    val listCustomer by viewModelCustomer.searchCustomerUiState.collectAsStateWithLifecycle()
    var expandedCustomer by remember { mutableStateOf(false) }
    ModalBottomSheet(
        modifier = modifier.height(500.dp),
        onDismissRequest = onDismiss
    ) {
        OutlinedTextField(
            value = searchCustomer,
            onValueChange = {
                searchCustomer = it
                viewModelCustomer.onChangeSearchQuery(searchCustomer)
                expandedCustomer = true
            },
            label = { Text("Product") },
            colors = customTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP)
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (val searchResult = listCustomer) {
            is SearchCustomerUiState.Loading -> IndeterminateCircularIndicator()
            is SearchCustomerUiState.Error -> NothingText()
            is SearchCustomerUiState.Success -> {
                if (searchResult.listSuggestionCustomer.isEmpty()) {
                    Text("No customers found", color = Color.Gray)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchResult.listSuggestionCustomer) { customer ->
                            CustomerItem(
                                modifier = Modifier.clickable {
                                    onClickCustomer(customer)
                                },
                                customer = customer
                            )
                        }
                    }
                }
            }
        }
    }
}