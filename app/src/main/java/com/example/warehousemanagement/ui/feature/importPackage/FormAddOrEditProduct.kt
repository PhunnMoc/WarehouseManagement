package com.example.warehousemanagement.ui.feature.importPackage

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.util.uploadImageToFirebase
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.InputArea
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.FormImportPackageViewModel
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchGenreUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchStorageLocationUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchSupplierUiState
import com.example.warehousemanagement.ui.theme.Dimens
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FormAddOrEditProductForm(
    modifier: Modifier = Modifier,
    onSubmit: (FormData) -> Unit,
    onAdd1MoreProduct: (String) -> Unit,
    onBackClick: () -> Unit,
    product: Product? = null,
    //onNavigateAddNewGenre: () -> Unit,
    viewModel: FormImportPackageViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf(product?.productName ?: "") }
    var genreName by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf<Genre?>(null) }
    var storageLocationName by remember { mutableStateOf("") }
    var storageLocation by remember { mutableStateOf<StorageLocation?>(null) }
    var supplierName by remember { mutableStateOf("") }
    var supplier by remember { mutableStateOf<Supplier?>(null) }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "") }
    var importPrice by remember { mutableStateOf(product?.importPrice?.toString() ?: "") }
    var exportPrice by remember { mutableStateOf(product?.sellingPrice?.toString() ?: "") }
    var isCheckedInStock by remember { mutableStateOf(product?.inStock ?: false) }
    var imageUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    val date by remember {
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    val listGenre by viewModel.searchGenreUiState.collectAsStateWithLifecycle()
    val listStorageLocation by viewModel.searchStorageLocationUiState.collectAsStateWithLifecycle()
    val listSupplier by viewModel.searchSupplierUiState.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = Dimens.PADDING_10_DP),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Product customerName
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.product_name_string)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            //Genre
            var expandedGenre by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = genreName,
                onValueChange = {
                    genreName = it
                    viewModel.onChangeSearchGenreQuery(it)
                    expandedGenre = true
                },
                label = { Text("Genre") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
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
                                                    genreName = option.genreName
                                                    genre = option
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


            //Quantity
            OutlinedTextField(
                value = quantity,
                onValueChange = { if (it.all { char -> char.isDigit() }) quantity = it },
                label = { Text(text = stringResource(id = R.string.quantity_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //importPrice
            OutlinedTextField(
                value = importPrice,
                onValueChange = { if (it.all { char -> char.isDigit() }) importPrice = it },
                label = { Text(text = stringResource(id = R.string.import_price_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //sellingPrice
            OutlinedTextField(
                value = exportPrice,
                onValueChange = { if (it.all { char -> char.isDigit() }) exportPrice = it },
                label = { Text(text = stringResource(id = R.string.selling_price_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //supplier
            var expandedSupplierLocation by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = supplierName,
                onValueChange = {
                    supplierName = it
                    viewModel.onChangeSearchSupplierQuery(it)
                    expandedSupplierLocation = true
                },
                label = { Text("Supplier") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
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
                                                    supplierName = option.name
                                                    supplier = option
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

            //storageLocationId
            var expandedStorageLocation by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = storageLocationName,
                onValueChange = {
                    storageLocationName = it
                    viewModel.onChangeSearchStorageLocationQuery(it)
                    expandedStorageLocation = true
                },
                label = { Text("Storage location") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            AnimatedVisibility(
                visible = expandedStorageLocation,
                enter = expandVertically(animationSpec = tween(durationMillis = 500)) + fadeIn(),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 500)) + fadeOut()
            ) {
                when (val storageLocations = listStorageLocation) {
                    is SearchStorageLocationUiState.Loading -> IndeterminateCircularIndicator()
                    is SearchStorageLocationUiState.Error -> NothingText()
                    is SearchStorageLocationUiState.Success -> {
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
                            if (storageLocations.listSearchStorageLocation.isEmpty()) {
                                Text(
                                    "No storage location found",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(Dimens.PADDING_5_DP)
                                )
                            } else {
                                FlowRow(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    storageLocations.listSearchStorageLocation.forEach { option ->
                                        Box(
                                            modifier = Modifier
                                                .padding(Dimens.PADDING_5_DP)
                                                .clip(RoundedCornerShape(Dimens.PADDING_10_DP))
                                                .background(MaterialTheme.colorScheme.primary)
                                                .clickable {
                                                    storageLocationName = option.storageLocationName
                                                    storageLocation = option
                                                    expandedStorageLocation = false
                                                }
                                                .padding(Dimens.PADDING_10_DP)
                                        ) {
                                            Text(
                                                text = option.storageLocationName,
                                                color = Color.White,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = "Add new StorageLocationName",
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

            //image
            UploadImageButton(onImageSelected = { imageUrl = it ?: "" })
            imageUrl.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = "Uploaded Image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            //  "inStock": true
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isCheckedInStock, onCheckedChange = { isCheckedInStock = it })
                Spacer(modifier = Modifier.width(8.dp))
                Text("In stock")
            }

            InputArea(
                label = "Description",
                value = description,
                onValueChange = { description = it }
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

// Chuyển từ LocalDate sang Date
                val date1: Date =
                    Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                BigButton(modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    enabled = name.isNotEmpty() && genre != null && storageLocation != null
                            && supplier != null
                            && quantity.isNotEmpty()
                            && importPrice.isNotEmpty()
                            && exportPrice.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {

                        viewModel.addProduct(
                            Product(
                                idProduct = "",
                                description = description,
                                genre = genre!!,
                                image = imageUrl,
                                importPrice = importPrice.toInt(),
                                inStock = isCheckedInStock,
                                lastUpdated = LocalDate.now().atStartOfDay()
                                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                productName = name,
                                quantity = quantity.toInt(),
                                sellingPrice = exportPrice.toInt(),
                                storageLocation = storageLocation!!,
                                supplier = supplier!!,
                            )
                        )
                        viewModel.addPackage(
                            date = LocalDate.now().atStartOfDay()
                                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        )
                    })
                IconButton(enabled = name.isNotEmpty() && genre != null && storageLocation != null
                        && supplier != null
                        && quantity.isNotEmpty()
                        && importPrice.isNotEmpty()
                        && exportPrice.isNotEmpty(),
                    onClick = {
                        viewModel.addProduct(
                            Product(
                                idProduct = "",
                                description = description,
                                genre = genre!!,
                                image = imageUrl,
                                importPrice = importPrice.toInt(),
                                inStock = isCheckedInStock,
                                lastUpdated = date,
                                productName = name,
                                quantity = quantity.toInt(),
                                sellingPrice = exportPrice.toInt(),
                                storageLocation = storageLocation!!,
                                supplier = supplier!!,
                            )
                        )
                        name = ""
                        genreName = ""
                        genre = null
                        storageLocationName = ""
                        storageLocation = null
                        supplierName = ""
                        supplier = null
                        quantity = ""
                        importPrice = ""
                        exportPrice = ""
                        isCheckedInStock = false
                        imageUrl = ""
                        description = ""
                    }) {
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

data class FormData(
    val name: String,
    val number: String,
    val date: String,
    val isChecked: Boolean,
    val fileName: String
)

@Composable
fun UploadImageButton(onImageSelected: (String?) -> Unit) {
    val context = LocalContext.current
    var imageName by remember { mutableStateOf("") }

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
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        // verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
            text = "Upload Image",
            color = Color.DarkGray
        )
        IconButton(modifier = Modifier
            .clip(
                shape = RoundedCornerShape(Dimens.PADDING_50_DP)
            )
            .background(color = colorResource(id = R.color.background_gray)), onClick = {
            launcher.launch(arrayOf("image/*"))
        }) {
            Icon(
                modifier = Modifier
                    .padding(Dimens.PADDING_10_DP)
                    .size(Dimens.SIZE_ICON_30_DP),
                painter = painterResource(id = R.drawable.upload),
                tint = colorResource(id = R.color.background_theme),
                contentDescription = ""
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewFormAddOrEditProduct() {
//    FormAddOrEditProductForm(
//        onSubmit = {},
//        onBackClick = {},
//        onAdd1MoreProduct = {},
//    )
//}