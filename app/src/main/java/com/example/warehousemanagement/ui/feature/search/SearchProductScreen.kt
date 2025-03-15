package com.example.warehousemanagement.ui.feature.search

import androidx.compose.foundation.Image
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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.CustomSearchBar
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductViewModel
import com.example.warehousemanagement.ui.theme.Dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchProductScreen(
    viewModel: SearchProductViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onClickDetailProduct: (String) -> Unit,
) {
    var searchValue by rememberSaveable { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val searchResults by viewModel.searchProductUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderOfScreen(
            mainTitleText = stringResource(id = R.string.screen_search_main_title),
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
        CustomSearchBar(
            modifier = Modifier,
            query = searchValue,
            onQueryChange = {
                viewModel.onChangeSearchQuery(it)
                searchValue = it
            },
            enabled = true,
            onSearch = {},
            active = true,
            onActiveChange = {},
            placeHolder = "Search for me",
            trailingIcon = {
                androidx.compose.material3.Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            searchValue = ""
                        },
                    tint = colorResource(id = R.color.text_color_dark_gray),
                    painter = painterResource(id = R.drawable.icons8_delete),
                    contentDescription = ""
                )
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
            Text(text = "Genre: ${product.genre?.genreName}")
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