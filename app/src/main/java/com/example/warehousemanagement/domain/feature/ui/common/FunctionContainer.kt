package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R

data class FunctionItem(
    val functionName: String,
    val iconResource: Int,
    val size: Dp,
    val color: Color,
    val shape: Shape,
    val textSize: TextUnit,
    val contentDescription: String? = null
)
@Composable
fun FunctionRow(functionItems: List<FunctionItem>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        functionItems.forEach { item ->
            ItemFunction(
                functionName = item.functionName,
                iconResource = item.iconResource,
                size = item.size,
                color = item.color,
                shape = item.shape,
                textSize = item.textSize,
                contentDescription = item.contentDescription,
                onClick = { /*TODO: Handle button click*/ }
            )
        }
    }
}

@Composable
fun AdminView() {
    val functionItems = List(3) {
        FunctionItem(
            "Function",
            R.drawable.icons8_square_function,
            100.dp,
            colorResource(id = R.color.icon_tint_white),
            RoundedCornerShape(8.dp),
            10.sp
        )
    }
    FunctionRow(functionItems)
    FunctionRow(functionItems)
}

@Composable
fun NonAdminView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "Shortcut",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold

        )
    }
    Spacer(modifier = Modifier.padding(5.dp))
    val functionItems = List(3) {
        FunctionItem(
            "Function",
            R.drawable.icons8_square_function,
            80.dp,
            colorResource(id = R.color.icon_tint_white),
            RoundedCornerShape(8.dp),
            10.sp
        )
    } + FunctionItem(
        "More",
        R.drawable.icons8_squared_menu_80,
        80.dp,
        colorResource(id = R.color.icon_tint_white),
        RoundedCornerShape(8.dp),
        10.sp
    )
    FunctionRow(functionItems)
}

@Composable
fun FunctionContainer(isAdmin: Boolean) {
    Column {
        if (isAdmin) {
            AdminView()
        } else {
            NonAdminView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFunctionContainer() {
    FunctionContainer(isAdmin = false)
}