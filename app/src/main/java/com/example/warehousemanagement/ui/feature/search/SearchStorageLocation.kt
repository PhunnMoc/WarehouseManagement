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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.ui.common.CustomSearchBar
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchProductViewModel
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchStorageLocationUiState
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchStorageLocationViewModel


@Composable
fun SearchStorageLocation(
    viewModel: SearchStorageLocationViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onClickDetailStorageLocation: (String) -> Unit,
) {
    var searchValue by rememberSaveable { mutableStateOf("") }

    val searchResults by viewModel.searchStorageLocationUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


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
                Icon(
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
            is SearchStorageLocationUiState.Loading -> IndeterminateCircularIndicator()
            is SearchStorageLocationUiState.Error -> NothingText()
            is SearchStorageLocationUiState.Success -> {
                if (searchResult.listSearchStorageLocation.isEmpty()) {
                    Text("No StorageLocations found", color = Color.Gray)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchResult.listSearchStorageLocation) { storageLocation ->
                            StorageLocationItem(
                                modifier = Modifier.clickable {
                                    onClickDetailStorageLocation(storageLocation.id)
                                },
                                storageLocation = storageLocation
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun StorageLocationItem(
    modifier: Modifier = Modifier,
    storageLocation: StorageLocation
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${storageLocation.id}", fontWeight = FontWeight.Bold)
            Text(text = "Name: ${storageLocation.storageLocationName}")
        }
    }
}
