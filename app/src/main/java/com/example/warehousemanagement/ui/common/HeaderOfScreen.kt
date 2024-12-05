package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderOfScreen(
    modifier: Modifier = Modifier,
    mainTitleText: String,
    startContent: @Composable() (() -> Unit)? = null,
    endContent: @Composable() (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White, // Màu nền của TopAppBar
            titleContentColor = colorResource(id = R.color.icon_tint_gray) // Màu tiêu đề
        ),
        title = {
            Text(
                text = mainTitleText,
                textAlign = TextAlign.Center,
                fontFamily = QuickSand,
                fontWeight = FontWeight.W800,
                fontSize = 17.sp
            )
        },
        navigationIcon = {
            startContent?.let {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    it()
                }

            }
        },
        actions = {
            endContent?.let {
                Row(
                    modifier = Modifier.padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    it()
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHeaderOfScreenMain() {
//    HeaderOfScreen(
//        mainTitleText = stringResource(id = R.string.screen_home_admin_main_title),
//        startContent = {
//            Image(painter = painterResource(id = R.drawable.icons8_back),
//                contentDescription = "Back",
//                modifier = Modifier
//                    .size(25.dp)
//                    .clickable {
//                        /*TODO: Implement back navigation*/
//                    })
//        },
//        endContent = {
//            Icon(
//                imageVector = Icons.Filled.Notifications,
//                contentDescription = "Notifications",
//                modifier = Modifier.size(25.dp)
//            )
//        },
//        scrollBehavior = scrollBehavior
//    )
//}