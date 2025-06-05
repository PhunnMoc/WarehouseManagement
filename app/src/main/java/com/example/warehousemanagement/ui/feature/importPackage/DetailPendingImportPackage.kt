package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun DetailPendingImportPackage(
    viewModel: DetailImportViewModel = hiltViewModel(),
    navigateToSetStorageLocationScreen: (String) -> Unit,
    onBack: () -> Unit,
    openObjectCounting: (String, Int) -> Unit,
) {

    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        val detailImportUiState by viewModel.detailPendingImportPackageUiState.collectAsStateWithLifecycle()
        val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
        when (val detail = detailImportUiState) {
            is DetailImportUiState.Loading -> IndeterminateCircularIndicator()
            is DetailImportUiState.Error -> NothingText()
            is DetailImportUiState.Success -> {
                ImportPackage(
                    user = currentUser,
                    importPackage = detail.detailImportPackage,
                    navigateToSetStorageLocationScreen = navigateToSetStorageLocationScreen,
                    onBack = onBack,
                    onUpdateImportPackage = viewModel::updateImportPackage,
                    openObjectCounting = openObjectCounting,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPackage(
    isPending: Boolean = true,
    modifier: Modifier = Modifier,
    user: User,
    importPackage: ImportPackages,
    navigateToSetStorageLocationScreen: (String) -> Unit,
    onBack: () -> Unit,
    onUpdateImportPackage: (String) -> Unit,
    openObjectCounting: (String, Int) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                                onBack()
                            })
                },
                endContent = {},
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (isPending) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BigButton(
                        onClick = {
                            onUpdateImportPackage("decline")
                            onBack()
                        },
                        enabled = true,
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f),
                        labelname = "Decline",
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    BigButton(
                        onClick = {
                            onUpdateImportPackage("approved")
                            navigateToSetStorageLocationScreen(importPackage.id)
                        },
                        enabled = true,
                        modifier = Modifier
                            .weight(1f),
                        labelname = "Next"
                    )
                }
            }
        }
    ) { innerPadding ->
        CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, end = 2.dp, bottom = 5.dp, top = 2.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(vertical = 10.dp, horizontal = 15.dp),
                        ) {
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                                    contentDescription = ""
                                )
                                Text(
                                    text = importPackage.importDate.toString(),
                                    fontFamily = QuickSand
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = if (importPackage.statusDone == "APPROVED") colorResource(
                                            id = R.color.background_done
                                        ) else colorResource(
                                            id = R.color.background_pending
                                        ),
                                        shape = RoundedCornerShape(50.dp),
                                    )
                                    .padding(Dimens.PADDING_10_DP),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    fontFamily = QuickSand,
                                    text = "Status: ${importPackage.statusDone}",
                                )
                            }
                        }

                    }
                    item {
                        CardInfoImportPackage(
                            packageName = importPackage.packageName,
                            packageDescription = importPackage.note,
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(vertical = 10.dp, horizontal = 15.dp)
                                .animateContentSize(animationSpec = tween(durationMillis = 500)), // Animation kích thước
                        ) {
                            Text(
                                modifier = Modifier.padding(Dimens.PADDING_10_DP),
                                text = "Check carefully the purchase price, selling price, and quantity of products before moving on to the next step because these products will be saved in the database.",
                                fontSize = 10.sp,
                            )
                            importPackage.listProducts.forEach { product ->
                                ProductItemDetail(
                                    openObjectCounting = openObjectCounting,
                                    product = product,
                                    shouldShowCounting = isPending,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItemDetail(
    modifier: Modifier = Modifier,
    product: Product,
    openObjectCounting: (String, Int) -> Unit,
    shouldShowCounting: Boolean,
) {
    var isCompactView by remember { mutableStateOf(false) }

    Row {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 15.dp),
        ) {

            Icon(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable { isCompactView = !isCompactView },
                tint = if (isCompactView) colorResource(id = R.color.background_done) else colorResource(
                    id = R.color.background_pending
                ),
                imageVector = if (isCompactView) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = "Toggle View"
            )

            // Animation cho nội dung hiển thị
            Column {
                AnimatedVisibility(visible = !isCompactView) {
                    ExpandedImportView(
                        shouldShowCounting = shouldShowCounting,
                        openObjectCounting = {
                            openObjectCounting(
                                product.productName,
                                product.quantity
                            )
                        },
                        product = product
                    )
                }
                if (isCompactView) {
                    Text(
                        fontWeight = FontWeight.W600,
                        fontSize = 15.sp,
                        text = product.productName,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandedImportView(
    shouldShowCounting: Boolean,
    openObjectCounting: () -> Unit,
    product: Product,
) {
    Column {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = rememberAsyncImagePainter(model = product.image),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f, false)) {
                Row {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${product.genre?.genreName}",
                            fontWeight = FontWeight.W600,
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.background_theme),
                        )
                        Text(
                            fontWeight = FontWeight.W600,
                            fontSize = 15.sp,
                            text = product.productName,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                    if (shouldShowCounting) {
                        Image(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { openObjectCounting() },
                            painter = painterResource(id = R.drawable.camera_tool),
                            contentDescription = "Delete"
                        )
                    }
                }
                Text(
                    text = "${product.description}",
                )
                Text(
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.text_color_light_gray),
                    text = "Supplier: ${product.supplier?.name}",
                )
                Text(
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.text_color_light_gray),
                    text = "Contact: ${product.supplier?.email}"
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

        }

        FlowRow {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Import Price: \$${product.importPrice}")
            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Selling Price: \$${product.sellingPrice}")

            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = colorResource(
                            id = R.color.background_pending
                        )
                    )
                    .padding(Dimens.PADDING_10_DP), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quantity: ${product.quantity}",
                )
            }
        }
    }
}



