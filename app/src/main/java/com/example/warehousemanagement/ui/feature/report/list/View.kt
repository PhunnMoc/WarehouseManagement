package com.example.warehousemanagement.ui.feature.report.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.ui.common.DialogView
import com.example.warehousemanagement.ui.theme.QuickSand


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardGenre(
    genre: Genre,
    moneyInStock: Float,
) {
    Card(shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f, false)
            ) {
                Text(
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_color_light_black),
                    text = genre.genreName,
                )
                Text(
                    fontFamily = QuickSand,
                    text = genre.idGenre,
                    color = colorResource(id = R.color.text_color_light_gray)
                )
            }
            Text(text = "\$$moneyInStock")
        }
    }
}


