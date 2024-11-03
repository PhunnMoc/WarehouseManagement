package com.example.warehousemanagement.ui.feature

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em

@Composable
fun ImportExportPackages() {
    Row(
        modifier = Modifier
            .size(width = 400.dp, height = 200.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        // Import Package Box
            PackageBox(
                label = "Import package",
                painter = painterResource(id = R.drawable.ic_opened_package) ,
                50,
                70,
                Alignment.BottomStart
            )

            // Export Package Box
            PackageBox(
                label = "Export package",
                painter = painterResource(id = R.drawable.ic_opened_package),
                -50,
                70,
                Alignment.BottomEnd

            )
    }
}

@Composable
fun PackageBox(label: String, painter: Painter, x:Int, y: Int, position:Alignment) {
    Box(
        modifier = Modifier
            .size(width = 170.dp, height = 200.dp)
            .background(
                color = colorResource(id = R.color.background_gray),
                shape = RoundedCornerShape(20.dp)
            ),

        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(width = 100.dp, height = 150.dp)
        )
        {
            Text(
                text = label,
                fontSize = 23.sp,
                color = Color.Black,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 2.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                ),

                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
            )
        }

        Box(modifier = Modifier.size(200.dp) )
        {
            Image(
                alignment = position,
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = (-x).dp, y = (y).dp) // Offset to display outside the outline
            )
        }
    }
}


@Composable
fun WorkerActivity() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header with Title and Notification Icon
        HeaderOfScreen(
            mainTitleText = stringResource(id = R.string.screen_customer_main_title),
            startContent = {
                Image(
                    painter = painterResource(id = R.drawable.icons8_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { /* TODO: Implement back navigation */ }
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


        ImportExportPackages()
        Spacer(modifier = Modifier.height(150.dp))
        FunctionContainer(isAdmin = false)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkerActivity() {
    WorkerActivity()
}
