package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.warehousemanagement.R

@Composable
fun HeaderOfScreen(isMainScreen: Boolean, mainTitleText: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isMainScreen) {
            IconButton(onClick = { /*TODO: Handle back button click*/ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        } else {
            Spacer(Modifier.weight(1f))
            Text(text = stringResource(id = mainTitleText), textAlign = TextAlign.Center)
            Spacer(Modifier.weight(1f))
        }

        IconButton(onClick = { /*TODO: Handle notification button click*/ }) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderOfScreen() {
    HeaderOfScreen(isMainScreen = false, mainTitleText = R.string.screen_home_admin_main_title)
}