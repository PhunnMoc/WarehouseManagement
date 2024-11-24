package com.example.warehousemanagement.ui.feature.search

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
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.test.product1
import com.example.warehousemanagement.test.product2
import com.example.warehousemanagement.test.product3
import com.example.warehousemanagement.test.product4
import com.example.warehousemanagement.ui.theme.Dimens


@Composable
fun SearchProductScreen(
    //   onSearch: (String, String) -> Unit,
    searchResults: List<Product>
) {
    var searchKey by rememberSaveable { mutableStateOf("") }
    var searchValue by rememberSaveable { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchValue,
            onValueChange = {
                isSearching = true
                searchValue = it },
            label = { Text("Value") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {

                        //  onSearch(searchKey, searchValue)
                    },
                    modifier = Modifier.align(Alignment.End),
                    enabled = searchKey.isNotEmpty() && searchValue.isNotEmpty()
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

        if (isSearching) {
            if (searchResults.isEmpty()) {
                Text("No products found", color = Color.Gray)
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(searchResults) { product ->
                        ProductItem(product = product)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${product.idProduct}", fontWeight = FontWeight.Bold)
            Text(text = "Name: ${product.productName}")
            Text(text = "Genre: ${product.genre.idGenre}")
            Text(text = "Price: ${product.sellingPrice}")
            Text(text = "Quantity: ${product.quantity}")
            Divider(Modifier.fillMaxWidth())
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchProductScreen() {
    SearchProductScreen(
        listOf(product1, product2, product3, product4)
    )
}