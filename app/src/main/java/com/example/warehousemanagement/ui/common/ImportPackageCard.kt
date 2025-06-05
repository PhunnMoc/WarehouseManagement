package com.example.warehousemanagement.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun ImportPackageCard(
    roleUiState: Boolean,
    modifier: Modifier = Modifier,
    importPackage: ImportPackages,
    onCardClick: () -> Unit,
    onLongPress: (String) -> Unit,
    onEditPendingPackage: ((String) -> Unit)? = null,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        backgroundColor = Color.White,
        modifier = modifier
            .shadow(
                elevation = Dimens.PADDING_2_DP,
                shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            )
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    isExpanded = !isExpanded
                    onCardClick()
                }, onLongPress = {
                    onLongPress(importPackage.id)
                })
            },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//                        color = if (importPackage.statusDone == "APPROVED" || importPackage.statusDone == "DECLINE") colorResource(
//                            id = R.color.background_done
//                        ) else colorResource(
//                            id = R.color.background_pending
//                        )
//                    )
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Package ID: ${importPackage.id}",
                    fontWeight = FontWeight.Bold,
                    fontFamily = QuickSand,
                )
                // QRCodeScreen(id = importPackage.idImportPackages)
                if (onEditPendingPackage != null && roleUiState) {
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onEditPendingPackage(importPackage.id) },
                        painter = painterResource(id = R.drawable.icons8_edit),
                        contentDescription = "Delete"
                    )
                }
            }
            Divider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.import_package_ic),
//                    contentDescription = "Product Image",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .padding(Dimens.PADDING_10_DP)
//                        .size(64.dp)
//                )
                QRCodeScreen(
                    modifier = Modifier
                        .padding(Dimens.PADDING_10_DP)
                        .size(64.dp),
                    id = importPackage.id
                )
                Column(
                    modifier = Modifier.padding(Dimens.PADDING_10_DP)
                ) {
                    Text(
                        text = "Package Name: ${importPackage.packageName}",
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color_light_black),
                        fontFamily = QuickSand,
                    )
                    Text(
                        text = "Status: ${importPackage.statusDone}",
                        fontWeight = FontWeight.W600,
                        fontSize = 15.sp,
                        color = if (importPackage.statusDone == "APPROVED" || importPackage.statusDone == "DECLINE") colorResource(
                            id = R.color.background_done
                        ) else colorResource(
                            id = R.color.background_pending
                        ),
                        fontFamily = QuickSand,
                    )
                }
            }

            if (isExpanded) {
                Column(
                    modifier = Modifier.padding(Dimens.PADDING_10_DP)
                ) {

                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Receiver Name:", weight = 3f)
                        TableCell(text = importPackage.receiver.username ?: "", weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Import Date:", weight = 3f)
                        TableCell(text = importPackage.importDate.toString(), weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Number of ExportProductDto:", weight = 3f)
                        TableCell(
                            modifier = Modifier.background(colorResource(id = R.color.background_light_theme)),
                            text = "${importPackage.listProducts.size}",
                            weight = 7f
                        )
                    }
                    Text(
                        text = "Note:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSand,
                        color = Color.Gray
                    )
                    Text(
                        modifier = Modifier.padding(top = Dimens.PADDING_10_DP),
                        text = importPackage.note,
                        fontFamily = QuickSand,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }

}
