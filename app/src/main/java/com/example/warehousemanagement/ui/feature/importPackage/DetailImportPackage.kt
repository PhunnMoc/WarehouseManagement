package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.TableCell
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun DetailImportPackage(
    viewModel: DetailImportViewModel = hiltViewModel(),
) {
    val detailImportUiState by viewModel.detailImportPackageUiState.collectAsStateWithLifecycle()

    when (val detail = detailImportUiState) {
        is DetailImportUiState.Loading -> IndeterminateCircularIndicator()
        is DetailImportUiState.Error -> NothingText()
        is DetailImportUiState.Success -> {
            ImportPackage(
                importPackage = detail.detailImportPackage,
                onEditClick = { productId ->
                    //    editingProduct = importPackage.listProducts.find { it.id == productId }
                }
            )
        }
    }
}

@Composable
fun ImportPackage(importPackage: ImportPackages, onEditClick: (String) -> Unit) {
    val scrollState = rememberLazyListState()
    val headerHeight by remember {
        derivedStateOf {
            (200 - scrollState.firstVisibleItemScrollOffset / 2).coerceAtLeast(0).dp
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PADDING_5_DP)
            ) {
                Text(
                    text = "Package Name: ${importPackage.packageName}",
                    fontWeight = FontWeight.W600,
                    fontSize = 25.sp,
                    // color = colorResource(id = R.color.text_color_light_black)
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(
                            color = if (importPackage.status == "APPROVED") colorResource(id = R.color.background_done) else colorResource(
                                id = R.color.background_pending
                            )
                        )
                        .padding(Dimens.PADDING_10_DP),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status: ${importPackage.status}",
                    )
                }
            }

            Column(

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        contentDescription = ""
                    )
                    Text(text = importPackage.importDate.toString())
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
            Row {
                Image(
                    painter = rememberAsyncImagePainter(model = importPackage.receiver.information?.picture),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(50.dp) // Thiết lập kích thước cho ảnh
                        .clip(CircleShape) // Cắt hình ảnh thành hình tròn
                        .border(
                            width = 2.dp, // Độ dày của viền
                            color = Color.Gray, // Màu sắc của viền
                            shape = CircleShape // Hình dạng viền
                        ) // Khoảng cách bên trong nếu cần
                )

                Column {
                    Text(text = "Receiver:", style = MaterialTheme.typography.subtitle1)
                    Text(text = "Email: ${importPackage.receiver.information?.email}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Note: ${importPackage.note}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Products:", style = MaterialTheme.typography.h6)
        }

        LazyColumn(state = scrollState) {
            items(importPackage.listProducts) { product ->
                ProductItemDetail(product = product, onEditClick = onEditClick)
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Handle Cancel action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = { /* Handle Decline action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                    ) {
                        Text(text = "Decline")
                    }
                    Button(
                        onClick = { /* Handle Approve action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                    ) {
                        Text(text = "Approve")
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItemDetail(product: Product, onEditClick: (String) -> Unit) {
    var isCompactView by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 500)), // Animation kích thước
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f, fill = false)) {
                    Text(
                        text = "Product Name: ${product.productName}",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W600
                    )
                    Text(text = "Genre: ${product.genre.genreName}")
                }
                IconButton(
                    onClick = { isCompactView = !isCompactView },
                ) {
                    Icon(
                        tint = if (isCompactView) colorResource(id = R.color.background_done) else colorResource(
                            id = R.color.background_pending
                        ),
                        imageVector = if (isCompactView) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
                        contentDescription = "Toggle View"
                    )
                }
            }

            // Animation cho nội dung hiển thị

            AnimatedVisibility(visible = !isCompactView) {
                ExpandedView(product = product)
            }
        }
    }
}

@Composable
fun CompactView(product: Product) {
    Column {
        Text(text = "ID: ${product.idProduct}", style = MaterialTheme.typography.subtitle1)
        Text(text = "Name: ${product.productName}", style = MaterialTheme.typography.h6)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandedView(product: Product) {

    Column {
        Divider(
            modifier = Modifier.padding(10.dp)
        )
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = rememberAsyncImagePainter(model = product.image),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            )
            Column(modifier = Modifier.weight(1f, false)) {
                Text(text = "Genre: ${product.genre.genreName}")
                Text(
                    text = "Description: ${product.description}",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Supplier: ${product.supplier.name}",
                )
                Text(text = "Contact: ${product.supplier.email}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Storage Location: ${product.storageLocation.storageLocationName}")

            }
        }

        FlowRow {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quantity: ${product.quantity}",
                )
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Import Price: \$${product.importPrice}")
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Selling Price: \$${product.sellingPrice}")

            }
        }


    }
}


@Composable
fun EditProductDialog(
    product: Product,
    onDismiss: () -> Unit,
    onSave: (Product) -> Unit
) {
    var quantity by remember { mutableStateOf(product.quantity.toString()) }
    var description by remember { mutableStateOf(product.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit Product") },
        text = {
            Column {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(product.copy(quantity = quantity.toInt(), description = description))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
