package com.example.warehousemanagement.ui.feature.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.common.SearchBarPreview

@Composable
fun StorageLocationDetailScreen(
    area: StorageLocation,
    products: List<Product>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        HeaderOfScreen(
            mainTitleText = area.storageLocationName,
            startContent = {
                Image(
                    painter = painterResource(id = R.drawable.icons8_back),
                    contentDescription = "Back",
                    modifier = androidx.compose.ui.Modifier
                        .size(25.dp)
                        .clickable {
                            /*TODO: Implement back navigation*/
                        }
                )
            },
            endContent = {
            }
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        SearchBarPreview()
        FilterAndSortButtons(onFilterClick = { /*TODO*/ }) { /*TODO*/ }
        // Hiển thị danh sách khu vực trong các thẻ
        LazyColumn {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onLongPress = {},
                    onCardClick = {},
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(5.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStorageLocationDetailScreen() {
    // StorageLocationDetailScreen(location1, listProduct, onBack = {})
}