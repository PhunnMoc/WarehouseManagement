package com.example.warehousemanagement.ui.feature.importPackage

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.util.uploadImageToFirebase
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.InputArea
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.FormImportPackageViewModel
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchGenreUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchSupplierUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.customTextFieldColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FormAddOrEditProductForm(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigationToHome: () -> Unit,
    //onNavigateAddNewGenre: () -> Unit,
    viewModel: FormImportPackageViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val listGenre by viewModel.searchGenreUiState.collectAsStateWithLifecycle()
    val listSupplier by viewModel.searchSupplierUiState.collectAsStateWithLifecycle()
    var productList by remember { mutableStateOf(mutableListOf(FormImportProductData())) }


    var packageName by remember { mutableStateOf("") }
    var packageDescription by remember { mutableStateOf("") }
    var shouldEnableSubmitButton by remember { mutableStateOf(false) }

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
                                onNavigationToHome()
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
                            listProducts = productList.map { it.toProduct() },
                            note = packageDescription,
                            packageName = packageName,
                            date = LocalDate.now().atStartOfDay()
                                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        )
                        onBackClick()
                    })
            }
        }
    ) { innerPadding ->
        CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
            FormImportProducts(
                modifier = Modifier.padding(innerPadding),
                listSupplier = listSupplier,
                listGenre = listGenre,
                upDateStatusSubmitButton = {
                    shouldEnableSubmitButton = it
                },
                onRemove = { index ->
                    productList = productList.toMutableList().apply { removeAt(index) }
                },
                onProductChange = { updatedProduct, index ->
                    productList = productList.toMutableList().apply { set(index, updatedProduct) }
                },
                productList = productList,
                onAddOneMoreProduct = {
                    productList = productList.toMutableList().apply { add(FormImportProductData()) }
                },
                packageName = packageName,
                packageDescription = packageDescription,
                onUpdatePackageName = {
                    packageName = it
                },
                onUpdatePackageDescription = {
                    packageDescription = it
                },
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FormImportProduct(
    index: String = "0",
    listSupplier: SearchSupplierUiState,
    listGenre: SearchGenreUiState,
    onRemove: () -> Unit,
    onChangeSearchGenreQuery: (String) -> Unit,
    onChangeSearchSupplierQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
    onProductChange: (FormImportProductData) -> Unit,
    product: FormImportProductData = FormImportProductData(),
) {
    val productData = remember { product }
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
            // Product customerName (70%)
            OutlinedTextField(
                value = productData.name,
                onValueChange = {
                    productData.name = it
                    onProductChange(productData.copy(name = it))
                },
                label = {
                    Text(
                        fontFamily = QuickSand,
                        text = stringResource(id = R.string.product_name_string)
                    )
                },
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                colors = customTextFieldColors(),
            )

            // Quantity (30%)
            OutlinedTextField(
                value = productData.quantity,
                maxLines = 1,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        productData.quantity = it
                        onProductChange(productData.copy(quantity = it))
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

        //Genre
        var expandedGenre by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = productData.genreName,
            onValueChange = {
                productData.genreName = it
                onProductChange(productData.copy(genreName = it))
                onChangeSearchGenreQuery(it)
                expandedGenre = true
            },
            label = { Text(fontFamily = QuickSand, text = "Genre") },
            modifier = Modifier.fillMaxWidth(),
            colors = customTextFieldColors(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
        )

        AnimatedVisibility(
            visible = expandedGenre,
            enter = expandVertically(animationSpec = tween(durationMillis = 500)) + fadeIn(),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 500)) + fadeOut()
        ) {
            when (val genres = listGenre) {
                is SearchGenreUiState.Loading -> IndeterminateCircularIndicator()
                is SearchGenreUiState.Error -> NothingText()
                is SearchGenreUiState.Success -> {
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
                        if (genres.listGenre.isEmpty()) {
                            Text(
                                "No genre found",
                                color = Color.Gray,
                                modifier = Modifier.padding(Dimens.PADDING_5_DP)
                            )
                        } else {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                genres.listGenre.forEach { option ->
                                    Box(
                                        modifier = Modifier
                                            .padding(Dimens.PADDING_5_DP)
                                            .clip(RoundedCornerShape(Dimens.PADDING_10_DP))
                                            .background(MaterialTheme.colorScheme.primary)
                                            .clickable {
                                                productData.genreName = option.genreName
                                                productData.genre = option
                                                onProductChange(productData.copy(genre = option))
                                                expandedGenre = false
                                            }
                                            .padding(Dimens.PADDING_10_DP)
                                    ) {
                                        Text(
                                            text = option.genreName,
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "Add new Genre",
                                modifier = Modifier
                                    .padding(top = Dimens.PADDING_10_DP)
                                    .clickable { /* Handle Add New Genre */ },
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.PADDING_10_DP) // Khoảng cách giữa các trường
        ) {
            // Import Price
            OutlinedTextField(
                value = productData.importPrice,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        productData.importPrice = it
                        onProductChange(productData.copy(importPrice = it))
                    }
                },
                label = {
                    Text(
                        fontFamily = QuickSand,
                        text = stringResource(id = R.string.import_price_title)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), // Chiếm toàn bộ không gian phân bổ
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = customTextFieldColors(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                trailingIcon = {
                    Text(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        text = "đ"
                    )
                }
            )

            // Selling Price
            OutlinedTextField(
                value = productData.exportPrice,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        productData.exportPrice = it
                        onProductChange(productData.copy(exportPrice = it))
                    }
                },
                label = {
                    Text(
                        fontFamily = QuickSand,
                        text = stringResource(id = R.string.selling_price_title)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(), // Chiếm toàn bộ không gian phân bổ
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = customTextFieldColors(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                trailingIcon = {
                    Text(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        text = "đ"
                    )
                }
            )
        }

        //supplier
        var expandedSupplierLocation by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = productData.supplierName,
            onValueChange = {
                productData.supplierName = it
                onProductChange(productData.copy(supplierName = it))
                onChangeSearchSupplierQuery(it)
                expandedSupplierLocation = true
            },
            label = {
                Text(
                    fontFamily = QuickSand,
                    text = "Supplier"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = customTextFieldColors(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
        )

        AnimatedVisibility(
            visible = expandedSupplierLocation,
            enter = expandVertically(animationSpec = tween(durationMillis = 500)) + fadeIn(),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 500)) + fadeOut()
        ) {
            when (val suppliers = listSupplier) {
                is SearchSupplierUiState.Loading -> IndeterminateCircularIndicator()
                is SearchSupplierUiState.Error -> NothingText()
                is SearchSupplierUiState.Success -> {
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
                        if (suppliers.listSupplier.isEmpty()) {
                            Text(
                                "No supplier found",
                                color = Color.Gray,
                                modifier = Modifier.padding(Dimens.PADDING_5_DP)
                            )
                        } else {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                suppliers.listSupplier.forEach { option ->
                                    Box(
                                        modifier = Modifier
                                            .padding(Dimens.PADDING_5_DP)
                                            .clip(RoundedCornerShape(Dimens.PADDING_10_DP))
                                            .background(MaterialTheme.colorScheme.primary)
                                            .clickable {
                                                productData.supplierName = option.name
                                                productData.supplier = option
                                                onProductChange(productData.copy(supplier = option))
                                                expandedSupplierLocation = false
                                            }
                                            .padding(Dimens.PADDING_10_DP)
                                    ) {
                                        Text(
                                            text = option.name,
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "Add new Supplier",
                                modifier = Modifier
                                    .padding(top = Dimens.PADDING_10_DP)
                                    .clickable { /* Handle Add New Genre */ },
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        //image
        UploadImageButton(onImageSelected = {
            productData.imageUrl = it ?: ""
            onProductChange(productData.copy(imageUrl = it ?: ""))
        })

        InputArea(
            label = "Description",
            value = productData.description,
            onValueChange = {
                productData.description = it
                onProductChange(productData.copy(description = it))
            }
        )
        var isUpdating by remember { mutableStateOf(false) }
    }
}

class FormImportProductData() {
    var name by mutableStateOf("")
    var genreName by mutableStateOf("")
    var genre by mutableStateOf<Genre?>(null)
    var supplierName by mutableStateOf("")
    var supplier by mutableStateOf<Supplier?>(null)
    var quantity by mutableStateOf("")
    var importPrice by mutableStateOf("")
    var exportPrice by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var description by mutableStateOf("")
    val date by mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

    fun copy(
        name: String = this.name,
        genreName: String = this.genreName,
        genre: Genre? = this.genre,
        supplierName: String = this.supplierName,
        supplier: Supplier? = this.supplier,
        quantity: String = this.quantity,
        importPrice: String = this.importPrice,
        exportPrice: String = this.exportPrice,
        imageUrl: String = this.imageUrl,
        description: String = this.description
    ): FormImportProductData {
        return FormImportProductData().apply {
            this.name = name
            this.genreName = genreName
            this.genre = genre
            this.supplierName = supplierName
            this.supplier = supplier
            this.quantity = quantity
            this.importPrice = importPrice
            this.exportPrice = exportPrice
            this.imageUrl = imageUrl
            this.description = description
        }
    }


    // Reset dữ liệu nhập
    fun clear() {
        name = ""
        genreName = ""
        genre = null
        supplierName = ""
        supplier = null
        quantity = ""
        importPrice = ""
        exportPrice = ""
        imageUrl = ""
        description = ""
    }

    fun toProduct(): Product {
        return Product(
            description = this.description,
            genre = this.genre,
            idProduct = "", // Tạo ID ngẫu nhiên (hoặc sửa lại theo yêu cầu)
            image = this.imageUrl,
            importPrice = this.importPrice.toIntOrNull()
                ?: 0, // Chuyển đổi String -> Int, nếu lỗi thì mặc định là 0
            inStock = (this.quantity.toIntOrNull() ?: 0) > 0, // Nếu số lượng > 0 thì còn hàng
            lastUpdated = this.date,
            productName = this.name,
            quantity = this.quantity.toIntOrNull() ?: 0,
            sellingPrice = this.exportPrice.toIntOrNull() ?: 0,
            storageLocation = null, // Nếu có field trong FormProductData, bạn có thể cập nhật
            supplier = this.supplier
        )
    }

    fun isFill(): Boolean {
        return name.isNotEmpty() &&
                genre != null &&
                supplier != null &&
                quantity.isNotEmpty() &&
                importPrice.isNotEmpty() &&
                exportPrice.isNotEmpty()
    }
}

fun List<Product>.toFormImportProductDataList(): List<FormImportProductData> {
    return this.map {
        FormImportProductData().apply {
            name = it.productName
            genre = it.genre
            supplier = it.supplier
            quantity = it.quantity.toString()
            importPrice = it.importPrice.toString()
            exportPrice = it.sellingPrice.toString()
            imageUrl = it.image
        }

    }
}

@Composable
fun FormImportProducts(
    productList: List<FormImportProductData>,
    modifier: Modifier = Modifier,
    listSupplier: SearchSupplierUiState,
    listGenre: SearchGenreUiState,
    viewModel: FormImportPackageViewModel = hiltViewModel(),
    upDateStatusSubmitButton: (Boolean) -> Unit,
    onRemove: (Int) -> Unit,
    onProductChange: (FormImportProductData, Int) -> Unit,
    onAddOneMoreProduct: () -> Unit,
    packageName: String,
    packageDescription: String,
    onUpdatePackageName: (String) -> Unit,
    onUpdatePackageDescription: (String) -> Unit,
) {

    val isAllFull = productList.all { it.isFill() }

    LaunchedEffect(isAllFull) {
        upDateStatusSubmitButton(isAllFull)
    }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        item {
            CardInfoImportPackage(
                packageName = packageName,
                packageDescription = packageDescription,
                onUpdatePackageName = onUpdatePackageName,
                onUpdatePackageDescription = onUpdatePackageDescription,
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
                productList.mapIndexed { index, item ->
                    FormImportProduct(
                        index = (index + 1).toString(),
                        listGenre = listGenre,
                        listSupplier = listSupplier,
                        onChangeSearchSupplierQuery = viewModel::onChangeSearchSupplierQuery,
                        onChangeSearchGenreQuery = viewModel::onChangeSearchGenreQuery,
                        onRemove = {
                            onRemove(index)
                        },
                        onProductChange = { updatedProduct ->
                            onProductChange(updatedProduct, index)
                        },
                        product = item
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }

                BigButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    enabled = isAllFull,
                    onClick = onAddOneMoreProduct,
                    labelname = "+ Add new product"
                )
                TotalImportPackage()
            }
        }
    }
}

@Composable
fun TotalImportPackage() {
    CompositionLocalProvider(
        LocalTextStyle provides TextStyle(
            fontFamily = QuickSand,
            fontWeight = FontWeight.W600,
            fontSize = 15.sp,
            color = Color.Gray,
        )
    ) {
        Divider()
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Number of products")
                Text(text = "0 products")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total purchase price")
                Text(text = "0 đ")
            }
        }
    }
}

@Composable
fun CardInfoImportPackage(
    packageName: String,
    packageDescription: String,
    onUpdatePackageName: (String) -> Unit = {},
    onUpdatePackageDescription: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 15.dp),
    ) {
        val date by remember {
            mutableStateOf(
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            )
        }
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = "Package's Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        // Improt package name
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
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = "Create an import package and another employee will check it before it is officially put into the warehouse.",
            fontSize = 10.sp,
        )
    }
}

@Composable
fun UploadImageButton(
    modifier: Modifier = Modifier,
    isText: Boolean = true,
    isShowPic: Boolean = true,
    onImageSelected: (String?) -> Unit
) {
    val context = LocalContext.current
    var imageName by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    if (ContextCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
        )
    }
    val coroutineScope = rememberCoroutineScope()
    // Khởi tạo ActivityResultLauncher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            // Lấy tên file từ Uri
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                imageName = cursor.getString(nameIndex) ?: "Unknown Image"
            }
            uploadImageToFirebase(
                coroutineScope = coroutineScope,
                uri = uri,
                onSuccess = {
                    print("Iris ok $it ")
                    onImageSelected(it)
                    imageUrl = it
                },
                onFailure = {
                    print("Iris máaa ${it.stackTrace}")
                }
            )
            println("Iris $imageName")
            // Gửi URI của ảnh và tên ảnh qua callback
        }
    }

    // Giao diện Button
    Column(
        modifier = modifier,
        // verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isText) {
            Text(
                modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                text = "Upload Image",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(modifier = Modifier
                .padding(5.dp)
                .clip(
                    shape = RoundedCornerShape(Dimens.PADDING_50_DP)
                )
                .background(color = colorResource(id = R.color.background_gray)),
                onClick = {
                    launcher.launch(arrayOf("image/*"))
                }) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.upload),
                    tint = colorResource(id = R.color.background_theme),
                    contentDescription = ""
                )
            }
            if (isShowPic) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Uploaded Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}
