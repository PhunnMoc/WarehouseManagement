package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    iconResource: Int =R.drawable.package_image,
    size: Dp,
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
                spotColor=colorResource(id = R.color.background_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = color),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .size(size)
            ,
            painter = painterResource(id =iconResource), // Use the passed resource id
            contentDescription = contentDescription ?: functionName
        )
        Text(
            modifier=Modifier.padding(Dimens.PADDING_10_DP),
            text = functionName,
            fontSize = textSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W600,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemFunction() {
    ItemFunction(
        functionName = "Function",
        iconResource = R.drawable.ic_opened_package,
        size = 100.dp,
        textSize = 12.sp,
        contentDescription = "Function",
        onClick = { /* Handle click event here */ }
    )
}