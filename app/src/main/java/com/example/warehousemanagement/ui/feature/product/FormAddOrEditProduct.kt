package com.example.warehousemanagement.ui.feature.product

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.theme.Dimens
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddOrEditProductForm(
    modifier: Modifier = Modifier,
    onSubmit: (FormData) -> Unit,
    onAdd1MoreProduct: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var selectedOption by rememberSaveable { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")
    var number by rememberSaveable { mutableStateOf("") }
    var isChecked by rememberSaveable { mutableStateOf(false) }
    var fileName by rememberSaveable { mutableStateOf("") }
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    val date by remember {
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    label = { Text("Genre") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                )
                IconButton(modifier = Modifier
                    .zIndex(1f)
                    .align(Alignment.BottomEnd),
                    onClick = { expandedGenre = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_mini_button),
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = expandedGenre,
                    onDismissRequest = { expandedGenre = false },
                    modifier = Modifier
                        .padding(horizontal = Dimens.PADDING_10_DP)
                        .fillMaxWidth()

                ) {
                    options.forEach { option -> //TODO()
                        DropdownMenuItem(onClick = {
                            selectedOption = option
                            expandedGenre = false
                        }) {
                            Text(option)
                        }
                    }
                    Text(text = "Add new Genre")
                }
            }

            //Quantity
            OutlinedTextField(
                value = number,
                onValueChange = { if (it.all { char -> char.isDigit() }) number = it },
                label = { Text(text = stringResource(id = R.string.quantity_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //importPrice
            OutlinedTextField(
                value = number,
                onValueChange = { if (it.all { char -> char.isDigit() }) number = it },
                label = { Text(text = stringResource(id = R.string.import_price_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //sellingPrice
            OutlinedTextField(
                value = number,
                onValueChange = { if (it.all { char -> char.isDigit() }) number = it },
                label = { Text(text = stringResource(id = R.string.selling_price_title)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            //supplier
            var expandedSupplier by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    label = { Text("Genre") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                )
                IconButton(modifier = Modifier
                    .zIndex(1f)
                    .align(Alignment.BottomEnd),
                    onClick = { expandedSupplier = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_mini_button),
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = expandedSupplier,
                    onDismissRequest = { expandedSupplier = false },
                    modifier = Modifier
                        .padding(horizontal = Dimens.PADDING_10_DP)
                        .fillMaxWidth()
                ) {
                    options.forEach { option -> //TODO()
                        DropdownMenuItem(onClick = {
                            selectedOption = option
                            expandedSupplier = false
                        }) {
                            Text(option)
                        }
                    }
                    Text(text = "Add new Supplier")
                }
            }

            //storageLocationId
            var expandedStorageLocation by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    label = { Text("Genre") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                )
                IconButton(modifier = Modifier
                    .zIndex(1f)
                    .align(Alignment.BottomEnd),
                    onClick = { expandedStorageLocation = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_mini_button),
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = expandedStorageLocation,
                    onDismissRequest = { expandedStorageLocation = false },
                    modifier = Modifier
                        .padding(horizontal = Dimens.PADDING_10_DP)
                        .fillMaxWidth()
                ) {
                    options.forEach { option -> //TODO()
                        DropdownMenuItem(onClick = {
                            selectedOption = option
                            expandedStorageLocation = false
                        }) {
                            Text(option)
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
                    .fillMaxWidth()
                    .clickable { showDatePicker = true },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Gray,
                ),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )

            // Checkbox
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("I agree to the terms and conditions")
//        }

            // Upload File Button
//        Button(onClick = { fileName = "SelectedFile.pdf" }) { // Simulate file selection
//            Text(if (fileName.isEmpty()) "Upload File" else "File: $fileName")
//        }
            //image
            UploadImageButton(onImageSelected = {})

            //  "inStock": true
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
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

                BigButton(modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    enabled = name.isNotEmpty() && selectedOption.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {
                        onSubmit(
                            FormData(
                                name = name,
                                selectedOption = selectedOption,
                                number = number,
                                date = date,
                                isChecked = isChecked,
                                fileName = fileName
                            )
                        )
                    })
                IconButton(
                    enabled = name.isNotEmpty() && selectedOption.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty(),
                    onClick = { onAdd1MoreProduct(TODO()) }) {
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
    val selectedOption: String,
    val number: String,
    val date: String,
    val isChecked: Boolean,
    val fileName: String
)

@Composable
fun UploadImageButton(onImageSelected: (Uri?) -> Unit) {
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
            onImageSelected(uri)
            println("Iris $imageName")
            // Gửi URI của ảnh và tên ảnh qua callback
        }
    }

    // Giao diện Button
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        // verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
            text = "Upload file",
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
                    .padding(Dimens.PADDING_20_DP)
                    .size(Dimens.SIZE_ICON_30_DP),
                painter = painterResource(id = R.drawable.upload),
                tint = colorResource(id = R.color.background_theme),
                contentDescription = ""
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormAddOrEditProduct() {
    FormAddOrEditProductForm(
        onSubmit = {},
        onBackClick = {},
        onAdd1MoreProduct = {},
    )
}