package com.example.warehousemanagement.ui.feature.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalAllImportPackageScreen(
    modifier: Modifier = Modifier,
    onClickAddProduct: () -> Unit,
    onClickAddProductByExcel: () -> Unit,
    onBackClick: () -> Unit,
    onClickSearch: () -> Unit,
    onNavigationDetailProduct: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isFilter by remember {
        mutableStateOf(false)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        topBar = {
            HeaderOfScreen(
                modifier = modifier.padding(
                    top = Dimens.PADDING_20_DP,
                    start = Dimens.PADDING_20_DP,
                    end = Dimens.PADDING_20_DP,
                    bottom = Dimens.PADDING_10_DP
                ),
                mainTitleText = stringResource(id = R.string.screen_product_main_title),
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
        }) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            //Spacer(modifier = Modifier.height(16.dp))

//            when (val product = productUiState) {
//                is ProductUiState.Loading -> IndeterminateCircularIndicator()
//                is ProductUiState.Error -> NothingText()
//                is ProductUiState.Success -> {
//                    LazyColumn(modifier = Modifier.padding(Dimens.PADDING_10_DP)) {
//                        items(product.listProduct) { product ->
//                            ProductCard(
//                                product = product,
//                                onCardClick = {},
//                                onLongPress = onNavigationDetailProduct,
//                            )
//                            Spacer(modifier = Modifier.height(8.dp))
//                        }
//                    }
//                }
//            }
//            if (isFilter) {
//                FilterProductScreen()
//            }
        }
    }

}

