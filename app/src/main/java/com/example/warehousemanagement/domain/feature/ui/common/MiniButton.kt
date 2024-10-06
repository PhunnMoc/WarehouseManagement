package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MiniButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(50.dp)
    ) {
        Text(text = text)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMiniButton() {
    MiniButton(text = "+", onClick = { /*TODO: Handle button click*/ })
}