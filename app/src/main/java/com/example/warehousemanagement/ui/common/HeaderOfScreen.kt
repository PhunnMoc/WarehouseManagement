package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R

@Composable
fun HeaderOfScreen(
    mainTitleText: String,
    startContent: @Composable (() -> Unit)? = null,
    endContent: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = mainTitleText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 25.sp)
        )
        startContent?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                it()
            }
        }
        endContent?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                it()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderOfScreenMain() {
    HeaderOfScreen(
        mainTitleText = stringResource(id = R.string.screen_home_admin_main_title),
        startContent = {
            Image(
                painter = painterResource(id = R.drawable.icons8_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        /*TODO: Implement back navigation*/
                    }
            )
        },
        endContent = {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(25.dp)
            )
        }
    )
}