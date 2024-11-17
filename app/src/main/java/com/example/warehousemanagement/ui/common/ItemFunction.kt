package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun ItemFunction(
    functionName: String,
    iconResource: Int = R.drawable.package_image,
    modifier: Modifier = Modifier,
    color: Color = colorResource(id = R.color.icon_tint_white),
    shape: Shape = RoundedCornerShape(8.dp),
    textSize: TextUnit = 10.sp,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(10.dp)
            .shadow(
                elevation = 20.dp,
                spotColor = colorResource(id = R.color.background_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .size(70.dp)
                .padding(5.dp)
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = colorResource(id = R.color.background_light_theme)
                )
                .wrapContentSize(),
        ) {
            Image(
                modifier = modifier.padding(Dimens.PADDING_10_DP),
                painter = painterResource(id = iconResource), // Use the passed resource id
                contentDescription = contentDescription ?: functionName
            )
        }
        Text(
            modifier = Modifier.padding(Dimens.PADDING_10_DP),
            text = functionName,
            color= colorResource(id = R.color.text_color_dark_gray),
            fontSize = textSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W600,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemFunction() {
    ItemFunction(functionName = "Function",
        iconResource = R.drawable.package_image,
        textSize = 12.sp,
        contentDescription = "Function",
        onClick = { /* Handle click event here */ })
}