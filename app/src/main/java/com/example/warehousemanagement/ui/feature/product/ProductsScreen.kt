package com.example.warehousemanagement.ui.feature.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.AIButton
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.ProductCard
import com.example.warehousemanagement.ui.common.SearchBarPreview
import com.example.warehousemanagement.ui.feature.chatBox.QuestionForChatBox
import com.example.warehousemanagement.ui.feature.filter.FilterProductScreen
import com.example.warehousemanagement.ui.feature.product.viewModel.ProductUiState
import com.example.warehousemanagement.ui.feature.product.viewModel.ProductViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    onNavigateToChatBox: (String) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onClickSearch: () -> Unit,
    onNavigationDetailProduct: (String) -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val productUiState by viewModel.productUiState.collectAsStateWithLifecycle()
    var isFilter by rememberSaveable {
        mutableStateOf(false)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(
                scrollBehavior = scrollBehavior,
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackClick()
                            })
                },
                mainTitleText = stringResource(id = R.string.screen_product_main_title),
                endContent = {
                    AIButton(
                        onClick = { onNavigateToChatBox(QuestionForChatBox.Product) },
                    )
                })
        }) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Dimens.PADDING_16_DP),
            ) {
                SearchBarPreview(
                    enabled = false,
                    modifier = Modifier.clickable {
                        onClickSearch()
                    }
                )
//                Button(onClick = { onClickSearch() }) {
//                    Text(text = "SEARCH")
//                }
                FilterAndSortButtons(onFilterClick = { isFilter = true }, onSortClick = {})
//                Button(onClick = { isFilter = true }) {
//                    Text(text = "Filter")
//                }
            }

            //Spacer(modifier = Modifier.height(16.dp))

            when (val product = productUiState) {
                is ProductUiState.Loading -> IndeterminateCircularIndicator()
                is ProductUiState.Error -> NothingText()
                is ProductUiState.Success -> {
                    LazyColumn(modifier = Modifier.padding(horizontal = Dimens.PADDING_20_DP)) {
                        items(product.listProduct) { product ->
                            ProductCard(
                                product = product,
                                onCardClick = {},
                                onLongPress = onNavigationDetailProduct,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }

        }
        if (isFilter) {
            FilterProductScreen(
                onClickFilter = viewModel::addFilterMap,
                onDismiss = { isFilter = false }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProductScreen() {
    WarehouseManagementTheme {
        //  ProductsScreen(products = listProduct)
    }
}