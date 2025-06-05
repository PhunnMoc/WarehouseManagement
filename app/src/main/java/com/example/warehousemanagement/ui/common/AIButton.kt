package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R

@Composable
fun AIButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedButton(onClick = onClick) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 10.dp),
                painter = painterResource(id = R.drawable.ai_icon), contentDescription = ""
            )
            Text(
                color = Color.Black,
                text = "Ask AI"
            )
        }
    }
}