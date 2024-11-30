package com.example.warehousemanagement.ui.feature.sort

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortSupplierScreen(
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
            SortIncreaseOrDecreasingSupplier() // Cùng một hàm sắp xếp tăng giảm như trong SortProductScreen
            SortByField(listSupplier = listOf(), onSortSelected ={})
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SortByField(
    listSupplier: List<Supplier>,
    onSortSelected: (String) -> Unit // Callback khi người dùng chọn trường sắp xếp
) {
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
            // Các trường sắp xếp có thể là: name, email, ratings
            listOf("name", "email", "ratings").forEach { field ->
                val fieldStringRes = when (field) {
                    "name" -> R.string.filter_field_name_supplier
                    "email" -> R.string.filter_field_email
                    "ratings" -> R.string.filter_field_ratings
                    else -> R.string.filter_field_name_supplier // Default value
                }

                Row(
                    modifier = Modifier
                        .padding(Dimens.PADDING_2_DP)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.LightGray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == field,
                        onClick = {
                            selectedOption = field
                            onSortSelected(field) // Truyền tên trường cho callback
                        }
                    )
                    Text(
                        modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                        text = stringResource(id = fieldStringRes) // Truyền resource string vào đây
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SortIncreaseOrDecreasingSupplier() {
    var selectedOption by rememberSaveable { mutableStateOf("Ascending") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(Dimens.PADDING_20_DP)
    ) {
        Text(
            text = stringResource(id = R.string.filter_tile_sort),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PADDING_5_DP),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Gray,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "Ascending",
                    onClick = { selectedOption = "Ascending" }
                )
                Text(
                    text = "Sort Ascending",
                    modifier = Modifier.padding(start = Dimens.PADDING_5_DP),
                    color = if (selectedOption == "Ascending") Color.Blue else Color.Gray
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "Descending",
                    onClick = { selectedOption = "Descending" }
                )
                Text(
                    text = "Sort Descending",
                    modifier = Modifier.padding(start = Dimens.PADDING_5_DP),
                    color = if (selectedOption == "Descending") Color.Blue else Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSortSupplierScreen() {
    SortSupplierScreen()
}
