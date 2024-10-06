package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import com.example.warehousemanagement.R

@Composable
fun HeaderOfScreen(isMainScreen: Boolean, mainTitleText: Int) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        //horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //Spacer(Modifier.weight(1f))
        Text(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center),
            text = stringResource(id = mainTitleText), textAlign = TextAlign.Center
        )
        //Spacer(Modifier.weight(1f))
        if (!isMainScreen) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start

            )
            {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }

        }

         else {

            Row(
                    modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End

                )
                    {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",

                        )
                    }

            }


        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewHeaderOfScreen() {
    HeaderOfScreen(isMainScreen = true, mainTitleText = R.string.screen_home_admin_main_title)
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderOfScreenMain() {
    HeaderOfScreen(isMainScreen = false, mainTitleText = R.string.screen_home_admin_main_title)
}