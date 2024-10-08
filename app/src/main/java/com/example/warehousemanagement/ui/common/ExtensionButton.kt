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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.WarehouseManagementTheme


@Composable
fun FilterAndSortButtons(
    //onFilterClick: () -> Unit,
    //onSortClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Filter Button
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier
                .clip(CircleShape)
                .height(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icons8_filter),
                contentDescription = "Sort Icon",
                tint= colorResource(id = R.color.text_color_black),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        // Sort Button
        Button(
            onClick ={ } ,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier
                .clip(CircleShape)
                .height(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icons8_sort),
                contentDescription = "Sort Icon",
                tint= colorResource(id = R.color.text_color_black),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sort by",color = colorResource(id = R.color.text_color_black))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ExtensionButtonPreview() {
    WarehouseManagementTheme {
        FilterAndSortButtons( )
    }
}