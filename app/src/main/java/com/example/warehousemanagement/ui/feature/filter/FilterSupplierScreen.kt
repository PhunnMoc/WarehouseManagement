package com.example.warehousemanagement.ui.feature.filter

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSupplierScreen(
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
            FilterByRate(listAddress = listOf())

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterByRate(listAddress: List<Address>) {
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
            listAddress.map { address ->
                Row(
                    modifier = Modifier
                        .padding(Dimens.PADDING_2_DP)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.LightGray), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedOption == address.city,
                        onClick = { selectedOption = address.city })
                    Text(
                        modifier = Modifier.padding(end = Dimens.PADDING_10_DP),
                        text = address.city,
                    )
                }
            }
        }

    }
}