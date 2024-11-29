package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun FinalFormImportPackage(
    listProduct: List<Product>
) {
    LazyColumn(modifier = Modifier.padding(Dimens.PADDING_10_DP)) {
        items(listProduct) { product ->
            ProductCard(
                product = product,
                onCardClick = {},
                onLongPress = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}