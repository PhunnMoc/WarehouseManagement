package com.example.warehousemanagement.ui.feature.exportPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.DetailExportUiState
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.DetailExportViewModel
import com.example.warehousemanagement.ui.feature.importPackage.ProductItemDetail
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun DetailExportPackage(
    viewModel: DetailExportViewModel = hiltViewModel(),
    navigateToSetStorageLocationScreen: (String) -> Unit,
    onBack: () -> Unit,
) {
    val detailExportUiState by viewModel.detailExportPackageUiState.collectAsStateWithLifecycle()
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    when (val detail = detailExportUiState) {
        is DetailExportUiState.Loading -> IndeterminateCircularIndicator()
        is DetailExportUiState.Error -> NothingText()
        is DetailExportUiState.Success -> {
            ExportPackage(
                user = currentUser,
                exportPackage = detail.detailExportPackage,
                navigateToSetStorageLocationScreen = navigateToSetStorageLocationScreen,
                onBack = onBack,
                onEditClick = { productId ->
                    //    editingProduct = importPackage.listProducts.find { it.id == productId }
                },
                onUpdateImportPackage = viewModel::updateImportPackage
            )
        }
    }
}

@Composable
fun ExportPackage(
    user: User,
    exportPackage: ExportPackages,
    navigateToSetStorageLocationScreen: (String) -> Unit,
    onEditClick: (String) -> Unit,
    onBack: () -> Unit,
    onUpdateImportPackage: () -> Unit,
) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.clickable { onBack() },
                        painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = exportPackage.packageName,
                        fontWeight = FontWeight.W600,
                        fontSize = 25.sp,
                        fontFamily = QuickSand
                        // color = colorResource(id = R.color.text_color_light_black)
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(
                            color = if (exportPackage.status == "APPROVED") colorResource(id = R.color.background_done) else colorResource(
                                id = R.color.background_pending
                            )
                        )
                        .padding(Dimens.PADDING_10_DP),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        fontFamily = QuickSand,
                        text = "Status: ${exportPackage.status}",
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
                    Text(
                        text = exportPackage.exportDate.toString(),
                        fontFamily = QuickSand
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
            Row {
                Image(
                    painter = rememberAsyncImagePainter(model = user.information?.picture),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(50.dp) // Thiết lập kích thước cho ảnh
                        .clip(CircleShape) // Cắt hình ảnh thành hình tròn
                        .border(
                            width = 2.dp, // Độ dày của viền
                            color = Color.Gray, // Màu sắc của viền
                            shape = CircleShape // Hình dạng viền
                        )
                )

                Column {
                    Text(
                        text = "Receiver:${user.information?.firstName + user.information?.lastName}",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(text = "Email: ${user.username}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Note: ${exportPackage.note}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = QuickSand,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ExportProductDto:", style = MaterialTheme.typography.h6)
        }

        LazyColumn(state = scrollState) {
            items(exportPackage.listProduct) { product ->
                ProductItemDetail(product = product, onEditClick = onEditClick)
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),

                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            onUpdateImportPackage()
                            onBack()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.line_light_gray)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(text = "Decline")
                    }
                    Button(
                        onClick = {
                            onUpdateImportPackage()
                            onBack()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_theme)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(text = "Approve")
                    }
                }
            }
        }
    }
}