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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchSupplierUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchSupplierViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSupplierScreen(
    viewModel: SearchSupplierViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onClickDetailSupplier: (String) -> Unit,
) {
    var searchValue by rememberSaveable { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val searchResults by viewModel.searchSupplierUiState.collectAsState()
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
        OutlinedTextField(
            value = searchValue,
            onValueChange = {
                searchValue = it
                viewModel.onChangeSearchQuery(searchValue)
            },
            label = { Text("Search Suppliers") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        // Perform search when clicking the icon if needed
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

        Spacer(modifier = Modifier.height(16.dp))

        when (val searchResult = searchResults) {
            is SearchSupplierUiState.Loading -> IndeterminateCircularIndicator()
            is SearchSupplierUiState.Error -> NothingText()
            is SearchSupplierUiState.Success -> {
                if (searchResult.listSupplier.isEmpty()) {
                    Text("No suppliers found", color = Color.Gray)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchResult.listSupplier) { supplier ->
                            SupplierItem(
                                modifier = Modifier.clickable {
                                    onClickDetailSupplier(supplier.idSupplier)
                                },
                                supplier = supplier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SupplierItem(
    modifier: Modifier = Modifier,
    supplier: Supplier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${supplier.idSupplier}", fontWeight = FontWeight.Bold)
            Text(text = "Name: ${supplier.name}")
            Text(text = "Email: ${supplier.email}")
            Text(text = "Address: ${supplier.address.street}, ${supplier.address.city}, ${supplier.address.postalCode}, ${supplier.address.district}",)
            Text(text = "Email: ${supplier.address.phone}")
            Text(text = "Rating: ${supplier.ratings ?: "N/A"}")
            Divider(Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchSupplierScreen() {
    SearchSupplierScreen(
        onBackClick = {},
        onClickDetailSupplier = {}
    )
}
