package com.example.warehousemanagement.domain.feature.ui.common
import androidx.compose.foundation.Image
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
fun HeaderOfScreen(isMainScreen: Boolean, mainTitleText: Int) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {

        Text(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center),
            text = stringResource(id = mainTitleText),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 25.sp)
        )

        if (!isMainScreen) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            )
            {
                Image(painter = painterResource(id = R.drawable.icons8_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(25.dp))
            }

        }

         else {

            Row(

                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically


                )
                    {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(25.dp)


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