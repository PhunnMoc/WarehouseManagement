package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R

@Composable
fun MiniButton(
    onClick: () -> Unit,
    icon: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {

    Icon(
        modifier = modifier.size(70.dp)
            .clickable { onClick() },
        painter = icon,
        tint = colorResource(id = R.color.background_theme),
        contentDescription = ""
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMiniButton() {
    MiniButton(
        onClick = { /* Handle button click */ },
        icon = painterResource(id = R.drawable.ic_add_mini_button),
        contentDescription = "Add Icon"
    )
}