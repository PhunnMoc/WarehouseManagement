package com.example.warehousemanagement.ui.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.data.mapper.convertToModel
import com.example.warehousemanagement.data.network.dto.DetailStorageResponse
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.feature.storage.viewModel.DetailStorageLocationUiState
import com.example.warehousemanagement.ui.feature.storage.viewModel.DetailStorageLocationViewmodel
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductBelongStorageScreen(
    onNavigationBack: () -> Unit,
    onNavigationDetailProduct: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailStorageLocationViewmodel = hiltViewModel(),
) {

    val detailStorageLocation by viewModel.detailStorageLocation.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(mainTitleText = "Products", startContent = {
                Image(painter = painterResource(id = R.drawable.icons8_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            onNavigationBack()
                        })
            }, endContent = {}, scrollBehavior = scrollBehavior
            )
        }) { innerpadding ->
        when (val detail = detailStorageLocation) {
            is DetailStorageLocationUiState.Loading -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
            ) {
                IndeterminateCircularIndicator()
            }

            is DetailStorageLocationUiState.Error -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerpadding)
            ) { NothingText() }

            is DetailStorageLocationUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpadding)
                        .padding(horizontal = 16.dp)
                ) {

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(vertical = 10.dp, horizontal = 15.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = detail.detailStorageLocation.storageImage),
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
                                Text(
                                    text = detail.detailStorageLocation.storageLocationId ?: "",
                                    fontFamily = QuickSand
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                fontFamily = QuickSand,
                                text = "Name: ${detail.detailStorageLocation.storageName}",
                            )

                            Text(
                                modifier = Modifier.padding(Dimens.PADDING_10_DP),
                                text = "Information of this storage location",
                                fontSize = 10.sp,
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(5.dp))
                    }

//            item {
//                Column(
//                    modifier = Modifier
//                        .padding(innerpadding)
//                        .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
//                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
//                        .background(Color.White)
//                        .padding(vertical = 10.dp, horizontal = 15.dp),
//                ) {
//
//                }
//            }
                    items(
                        detail.detailStorageLocation.listProduct ?: listOf()
                    ) { product ->
                        ProductCard(
                            modifier = Modifier,
                            product = product.convertToModel(),
                            onCardClick = {},
                            onLongPress = onNavigationDetailProduct,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

    }
}