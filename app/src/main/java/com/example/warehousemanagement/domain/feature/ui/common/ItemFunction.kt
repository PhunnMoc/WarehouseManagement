package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R



@Composable
fun ItemFunction(functionName: String, iconResource: Int, size: Dp) {
    Column(
        modifier = Modifier
            .width(size)
            .height(size + 20.dp) // Height of the item
    ) {
        Image(
            alignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(8.dp))
                .background(color = colorResource(id =R.color.icon_tint_white)),
            painter = painterResource(id = iconResource), // Use the passed resource id
            contentDescription = "Function Icon"
        )
        Text(text = functionName,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(size),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemFunction() {
    ItemFunction("Function", R.drawable.icons8_square_function, 100.dp)
}