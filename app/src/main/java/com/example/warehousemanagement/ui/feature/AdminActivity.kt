package com.example.warehousemanagement.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon

@Composable
fun HalfIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_opened_package),
        contentDescription = "Half Opened Package",
        modifier = Modifier
            .size(300.dp)
    )
}

@Composable
fun AdminActivity() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                WrapIcon(
                    idIcon = R.drawable.icons8_bell,
                    isNewNotification = true,
                )
            }
        )



        val listSuggestions = listOf("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
        SearchBarWithSuggestion(listSuggestions)
        FunctionContainer(isAdmin = true)
        HalfIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminActivity() {
    AdminActivity()
}