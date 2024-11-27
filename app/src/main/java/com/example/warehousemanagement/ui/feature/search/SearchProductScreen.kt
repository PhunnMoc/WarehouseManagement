package com.example.warehousemanagement.ui.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.feature.product.viewModel.ProductUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductViewModel
import com.example.warehousemanagement.ui.theme.Dimens


@Composable
fun SearchProductScreen(
    viewModel: SearchProductViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onClickDetailProduct: (String) -> Unit,
) {
    var searchValue by rememberSaveable { mutableStateOf("") }

    val searchResults by viewModel.searchProductUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchValue,
            onValueChange = {
                searchValue = it
                viewModel.onChangeSearchQuery(searchValue)

            },
            label = { Text("Value") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {

                        //  onSearch(searchKey, searchValue)
                    },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Icon(
                        modifier = Modifier.size(Dimens.SIZE_ICON_25_DP),
                        painter = painterResource(id = R.drawable.icons8_search),
                        contentDescription = ""
                    )
                }
            }
        )

        //  Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        when (val searchResult = searchResults) {
            is SearchProductUiState.Loading -> IndeterminateCircularIndicator()
            is SearchProductUiState.Error -> NothingText()
            is SearchProductUiState.Success -> {
                if (searchResult.listSuggestionProduct.isEmpty()) {
                    Text("No products found", color = Color.Gray)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchResult.listSuggestionProduct) { product ->
                            ProductItem(
                                modifier = Modifier.clickable {
                                    onClickDetailProduct(product.idProduct)
                                },
                                product = product
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${product.idProduct}", fontWeight = FontWeight.Bold)
            Text(text = "Name: ${product.productName}")
            Text(text = "Genre: ${product.genre.genreName}")
            Text(text = "Price: ${product.sellingPrice}")
            Text(text = "Quantity: ${product.quantity}")
            Divider(Modifier.fillMaxWidth())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchProductScreen() {
//    SearchProductScreen(
//        listOf(product1, product2, product3, product4)
//    )
}