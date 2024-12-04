package com.example.warehousemanagement.ui.feature.filter

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.feature.genre.viewModel.GenreUiState
import com.example.warehousemanagement.ui.feature.genre.viewModel.GenreViewModel
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationViewModel
import com.example.warehousemanagement.ui.feature.supplier.viewModel.SupplierUIState
import com.example.warehousemanagement.ui.feature.supplier.viewModel.SupplierViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterProductScreen(
    modifier: Modifier = Modifier,
    viewModelGenre: GenreViewModel = hiltViewModel(),
    viewModelSupplier: SupplierViewModel = hiltViewModel(),
    viewModelStorageLocation: StorageLocationViewModel = hiltViewModel(),
    onClickFilter: (String, String) -> Unit,
    onDismiss: () -> Unit,
) {
    val listGenre by viewModelGenre.genreUiState.collectAsStateWithLifecycle()
    val listSupplier by viewModelSupplier.supplierUIState.collectAsStateWithLifecycle()
    val listStorageLocation by viewModelStorageLocation.storageLocationUiState.collectAsStateWithLifecycle()
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            FilterByGenreId(
                listGenre = (listGenre as? GenreUiState.Success)?.listGenre ?: listOf(),
                onClickFilter = onClickFilter,
            )
            FilterBySupplierId(
                listSupplier = (listSupplier as? SupplierUIState.Success)?.listSupplier
                    ?: listOf(),
                onClickFilter = onClickFilter
            )
            FilterByStorageLocationId(
                listStorageLocation = (listStorageLocation as? StorageLocationUiState.Success)?.listStorageLocation
                    ?: listOf(),
                onClickFilter = onClickFilter
            )
            FilterBySellingPrice()
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Filter")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@Composable
fun FilterByGenreId(
    listGenre: List<Genre>,
    onClickFilter: (String, String) -> Unit,
) {
    var selectedOption by rememberSaveable { mutableStateOf("") }
    var showAll by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(Dimens.PADDING_20_DP)
    ) {
        Text(
            text = stringResource(id = R.string.filter_tile_genre),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PADDING_5_DP),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Gray,
        )
        AnimatedContent(
            targetState = showAll,
            transitionSpec = {
                fadeIn() with fadeOut() using SizeTransform(clip = false)
            }, label = ""
        ) { isExpanded ->
            val displayedGenres = if (isExpanded) listGenre else listGenre.take(5)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.PADDING_5_DP)
            ) {
                displayedGenres.forEach { genre ->
                    Row(
                        modifier = Modifier
                            .padding(Dimens.PADDING_2_DP)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == genre.idGenre,
                            onClick = {
                                selectedOption = genre.idGenre
                                onClickFilter("genreId", genre.idGenre)
                            }
                        )
                        Text(
                            modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                            text = genre.genreName,
                        )
                    }
                }
            }
        }

        // "See More" or "See Less" button
        Text(
            text = if (showAll) stringResource(id = R.string.see_less) else stringResource(id = R.string.see_more),
            color = colorResource(id = R.color.background_theme),
            modifier = Modifier
                .clickable { showAll = !showAll }
                .padding(top = Dimens.PADDING_10_DP)
        )
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@Composable
fun FilterBySupplierId(
    listSupplier: List<Supplier>,
    onClickFilter: (String, String) -> Unit,
) {
    var selectedOption by rememberSaveable { mutableStateOf("") }
    var showAll by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(Dimens.PADDING_20_DP)
    ) {
        Text(
            text = stringResource(id = R.string.filter_tile_supplier),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PADDING_5_DP),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Gray,
        )

        AnimatedContent(
            targetState = showAll,
            transitionSpec = {
                fadeIn() with fadeOut() using SizeTransform(clip = false)
            }, label = ""
        ) { isExpanded ->
            val displayedSuppliers = if (isExpanded) listSupplier else listSupplier.take(5)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.PADDING_5_DP)
            ) {
                displayedSuppliers.forEach { supplier ->
                    Row(
                        modifier = Modifier
                            .padding(Dimens.PADDING_2_DP)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == supplier.idSupplier,
                            onClick = {
                                selectedOption = supplier.idSupplier
                                onClickFilter("supplierId", supplier.idSupplier)
                            }
                        )
                        Text(
                            modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                            text = supplier.name,
                        )
                    }
                }
            }
        }

        Text(
            text = if (showAll) stringResource(id = R.string.see_less) else stringResource(id = R.string.see_more),
            color = colorResource(id = R.color.background_theme),
            modifier = Modifier
                .clickable { showAll = !showAll }
                .padding(top = Dimens.PADDING_10_DP)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@Composable
fun FilterByStorageLocationId(
    listStorageLocation: List<StorageLocation>,
    onClickFilter: (String, String) -> Unit,
) {
    var selectedOption by rememberSaveable { mutableStateOf("") }
    var showAll by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(Dimens.PADDING_20_DP)
    ) {
        Text(
            text = stringResource(id = R.string.filter_tile_supplier),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PADDING_5_DP),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Gray,
        )

        AnimatedContent(
            targetState = showAll,
            transitionSpec = {
                fadeIn() with fadeOut() using SizeTransform(clip = false)
            }, label = ""
        ) { isExpanded ->
            val displayedSuppliers =
                if (isExpanded) listStorageLocation else listStorageLocation.take(5)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.PADDING_5_DP)
            ) {
                displayedSuppliers.forEach { storageLocation ->
                    Row(
                        modifier = Modifier
                            .padding(Dimens.PADDING_2_DP)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == storageLocation.id,
                            onClick = {
                                selectedOption = storageLocation.id
                                onClickFilter("storageLocationId", storageLocation.id)
                            }
                        )
                        Text(
                            modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                            text = storageLocation.storageLocationName,
                        )
                    }
                }
            }
        }

        Text(
            text = if (showAll) stringResource(id = R.string.see_less) else stringResource(id = R.string.see_more),
            color = colorResource(id = R.color.background_theme),
            modifier = Modifier
                .clickable { showAll = !showAll }
                .padding(top = Dimens.PADDING_10_DP)
        )
    }
}


@Composable
fun FilterBySellingPrice(
    // onFilterChange: (String, String) -> Unit
) {
    var minPrice by rememberSaveable { mutableStateOf("") }
    var maxPrice by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(Dimens.PADDING_20_DP)
    ) {
        // Tiêu đề
        Text(
            text = stringResource(id = R.string.filter_tile_sellingPrice),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PADDING_5_DP),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Gray,
        )

        //  Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))

        // TextField cho giá tối thiểu
        Row {
            OutlinedTextField(
                value = minPrice,
                onValueChange = {
                    minPrice = it
                    errorMessage = "" // Xóa lỗi trước khi kiểm tra
                    validatePriceRange(minPrice, maxPrice)?.let {
                        errorMessage = it
                    }
                    //Todo onFilterChange(minPrice, maxPrice)
                },
                label = { Text(stringResource(id = R.string.min_price)) },
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimens.PADDING_5_DP),
                singleLine = true,
                isError = errorMessage.isNotEmpty()
            )

            //   Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))

            // TextField cho giá tối đa
            OutlinedTextField(
                value = maxPrice,
                onValueChange = {
                    maxPrice = it
                    errorMessage = "" // Xóa lỗi trước khi kiểm tra
                    validatePriceRange(minPrice, maxPrice)?.let {
                        errorMessage = it
                    }
                    //Todo onFilterChange(minPrice, maxPrice)
                },
                label = { Text(stringResource(id = R.string.max_price)) },
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimens.PADDING_5_DP),
                singleLine = true,
                isError = errorMessage.isNotEmpty()
            )
        }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(Dimens.PADDING_5_DP)
            )
        }
    }
}

fun validatePriceRange(minPrice: String, maxPrice: String): String? {
    val min = minPrice.toDoubleOrNull()
    val max = maxPrice.toDoubleOrNull()

    return when {
        min != null && max != null && min > max -> "Min price cannot be greater than Max price."
        else -> null
    }
}


@Preview
@Composable
fun PreviewFilterProductScreen() {
    //  FilterProductScreen(onClickFilter = viewModel::filter)
}