package com.example.warehousemanagement.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.Dimens.PADDING_10_DP

@Composable
fun HalfIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        alpha = 0.3f,
        painter = painterResource(id = R.drawable.package_image),
        contentDescription = "Half Opened Package",
        modifier = modifier,
    )
}

@Composable
fun AdminScreen(modifier: Modifier = Modifier) {
    Scaffold(
        containerColor = colorResource(id = R.color.background_white),
        modifier = modifier, 
        topBar = {
        HeaderOfScreen(modifier = modifier.padding(
            top = Dimens.PADDING_20_DP,
            start = Dimens.PADDING_20_DP,
            end = Dimens.PADDING_20_DP
        ),
            mainTitleText = stringResource(id = R.string.screen_home_admin_main_title),
            endContent = {
                WrapIcon(
                    tint = colorResource(id = R.color.icon_tint_gray),
                    modifier = Modifier.size(Dimens.SIZE_ICON_30_DP),
                    idIcon = R.drawable.icons8_bell,
                    isNewNotification = false,
                )
            })
    }) { innerpadding ->
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .padding(innerpadding)
                    // .fillMaxSize()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                val listSuggestions = listOf("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
                Row(
                    modifier=Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WrapIcon(
                        idIcon = R.drawable.scan_icon,
                        tint = colorResource(id = R.color.icon_tint_gray),
                        modifier = Modifier
                            .size(Dimens.SIZE_ICON_35_DP)
                            .padding(end = Dimens.PADDING_5_DP),
                    )
                    SearchBarWithSuggestion(listSuggestions)
                }

                FunctionContainer(isAdmin = true)
            }
            HalfIcon(
                modifier = Modifier.align(
                    BiasAlignment(horizontalBias = -5f, verticalBias = 1.25f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminActivity() {
    AdminScreen()
}