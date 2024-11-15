package com.example.warehousemanagement.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.test.product1
import com.example.warehousemanagement.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource

@Composable
fun ProductCard(
    product: Product, // Add this parameter
    qrCodeIconRes: Int, onCardClick: () -> Unit// Image resource id for the QR code icon
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    Card(
        backgroundColor = Color.White,
        //  shape = RoundedCornerShape(10.dp),
        //elevation = 10.dp,
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .animateContentSize()
            .clickable {
                isExpanded = !isExpanded
                onCardClick()
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            // verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_importpackage),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "ID: ${product.idProduct}",
                        color = colorResource(id = R.color.background_theme)
                    )
                    Text(
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color_light_black),
                        text = product.productName,
                    )

                    Text(
                        text = "Quantity: ${product.quantity}", fontSize = 14.sp, color = Color.Gray
                    )
                }
                Image(
                    painter = painterResource(id = qrCodeIconRes),
                    contentDescription = "QR Code Icon",
                    modifier = Modifier.size(50.dp)
                )
            }

            if (isExpanded) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Product name:", weight = 3f)
                        TableCell(text = product.productName, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Quantity:", weight = 3f)
                        TableCell(text = product.quantity.toString(), weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Import Price:", weight = 3f)
                        TableCell(text = product.importPrice.toString(), weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Supplier ID:", weight = 3f)
                        TableCell(text = product.supplierId, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "In Stock:", weight = 3f)
                        TableCell(text = if (product.isInStock) "Yes" else "No", weight = 7f)
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Product ID:", weight = 3f)
                        TableCell(text = product.idProduct, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Genre ID:", weight = 3f)
                        TableCell(text = product.genreId, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Selling Price:", weight = 3f)
                        TableCell(text = product.sellingPrice.toString(), weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Location ID:", weight = 3f)
                        TableCell(text = product.storageLocationId, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Last Updated:", weight = 3f)
                        TableCell(text = product.lastUpdated.toString(), weight = 7f)
                    }
                    Text(
                        text = "Description:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = product.description,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String, weight: Float
) {
    Text(
        text = text,
        Modifier
            .fillMaxHeight()
            .border(0.5.dp, Color.Gray)
            .weight(weight)
            .padding(8.dp),
        fontSize = 14.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(product1,
        qrCodeIconRes = R.drawable.ic_qr_code,
        onCardClick = { /* Handle card click here */ })
}
