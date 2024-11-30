package com.example.warehousemanagement.ui.feature.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun FilterProductScreen(
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            FilterByGenreId(listGenre = listOf())
            FilterBySupplierId(listSupplier = listOf())
            FilterBySellingPrice()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterByGenreId(listGenre: List<Genre>) {
    var selectedOption by rememberSaveable { mutableStateOf("") }

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
        FlowRow {
            listGenre.map { genre ->
                Row(
                    modifier = Modifier
                        .padding(Dimens.PADDING_2_DP)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.LightGray), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedOption == genre.idGenre,
                        onClick = { selectedOption = genre.idGenre })
                    Text(
                        modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                        text = genre.genreName,
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterBySupplierId(listSupplier: List<Supplier>) {
    var selectedOption by rememberSaveable { mutableStateOf("") }

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
        FlowRow {
            listSupplier.map { supplier ->
                Row(
                    modifier = Modifier
                        .padding(Dimens.PADDING_2_DP)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.LightGray), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedOption == supplier.idSupplier,
                        onClick = { selectedOption = supplier.idSupplier })
                    Text(
                        modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                        text = supplier.name,
                    )
                }
            }
        }
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
        Row{
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
        // Hiển thị lỗi nếu có
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(Dimens.PADDING_5_DP)
            )
        }
    }
}

// Hàm kiểm tra giá trị và trả về lỗi nếu có
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
    FilterProductScreen()
}