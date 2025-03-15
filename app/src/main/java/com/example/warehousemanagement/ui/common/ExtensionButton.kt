package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme


@Composable
fun FilterAndSortButtons(
    onFilterClick: () -> Unit, // Action for Filter Button
    onSortClick: () -> Unit // Action for Sort Button
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.PADDING_10_DP),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Filter Button
        CustomIconButton(
            text = "Filter", // No text for Filter button, only icon
            icon = painterResource(id = R.drawable.icons8_filter),
            onClick = onFilterClick // Action when Filter button is clicked
        )

        Spacer(modifier = Modifier.width(16.dp)) // Space between buttons

        // Sort Button
        CustomIconButton(
            text = "Sort by", // Text for Sort button
            icon = painterResource(id = R.drawable.icons8_sort),
            onClick = onSortClick // Action when Sort button is clicked
        )
    }
}

@Composable
fun CustomIconButton(
    text: String,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, // Action when button is clicked
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_white)),
        modifier = Modifier
            // .clip(CircleShape)
            .shadow(elevation = Dimens.PADDING_2_DP, shape = CircleShape)
            .height(40.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = null, // Icon description for accessibility
                tint = colorResource(id = R.color.text_color_dark_gray),
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            fontFamily = QuickSand,
            text = text,
            fontSize = 10.sp,
            color = colorResource(id = R.color.text_color_dark_gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExtensionButtonPreview() {
    WarehouseManagementTheme {
        FilterAndSortButtons(
            onFilterClick = {
                // Handle Filter button click
                println("Filter button clicked")
            },
            onSortClick = {
                // Handle Sort button click
                println("Sort button clicked")
            }
        )
    }
}