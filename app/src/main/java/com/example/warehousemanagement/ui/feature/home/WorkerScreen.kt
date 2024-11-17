package com.example.warehousemanagement.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun ImportExportPackages(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PADDING_16_DP),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        // Import Package Box
        PackageBox(
            label = "Import package",
            painter = painterResource(id = R.drawable.package_image),
            -2.5f,
        )

        // Export Package Box
        PackageBox(
            label = "Export package",
            painter = painterResource(id = R.drawable.package_image),
            2.5f,

            )
    }
}

@Composable
fun PackageBox(label: String, painter: Painter, x: Float) {
    Box(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(150.dp)
                .shadow(
                    elevation = 10.dp, shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = colorResource(id = R.color.background_gray),
                    shape = RoundedCornerShape(20.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = label,
                fontSize = 23.sp,
                lineHeight = 35.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
            )
        }
        Image(
            alpha = 0.75f,
            painter = painter, contentDescription = null, modifier = Modifier
                .size(100.dp)
                .align(
                    BiasAlignment(horizontalBias = x, verticalBias = 2.75f)
                )
        )

    }
}


@Composable
fun WorkerScreen(modifier: Modifier = Modifier) {
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier,
        topBar = {
            HeaderOfScreen(modifier = modifier.padding(
                top = Dimens.PADDING_20_DP,
                start = Dimens.PADDING_20_DP,
                end = Dimens.PADDING_20_DP
            ),
                mainTitleText = stringResource(id = R.string.screen_customer_main_title),
                endContent = {
                    WrapIcon(
                        tint = colorResource(id = R.color.icon_tint_gray),
                        modifier = Modifier.size(Dimens.SIZE_ICON_30_DP),
                        idIcon = R.drawable.icons8_bell,
                        isNewNotification = false,
                    )
                })
        }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
        ) {

            val listSuggestions = listOf("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
            Row(
                modifier = Modifier.padding(Dimens.PADDING_16_DP),
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

            Spacer(modifier = Modifier.weight(1f))
            ImportExportPackages()
            Spacer(modifier = Modifier.weight(1f))
//            FunctionContainer(
//               // modifier=Modifier.padding(bottom = Dimens.PADDING_20_DP),
//                isAdmin = false)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkerActivity() {
    WorkerScreen()
}
