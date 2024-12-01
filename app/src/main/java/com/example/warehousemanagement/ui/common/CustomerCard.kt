package com.example.warehousemanagement.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun CustomerCard(
    modifier: Modifier = Modifier, customer: Customer, // Add this parameter
    onCardClick: () -> Unit,
    onLongPress: (String) -> Unit,
    ) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.White,
        //  shape = RoundedCornerShape(10.dp),
        //elevation = 10.dp,
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    isExpanded = !isExpanded
                    onCardClick()
                }, onLongPress = {
                    onLongPress(customer.idCustomer)
                })
            },


        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            // verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.background_gray))
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Customer ID: ${customer.idCustomer}")
            }
            Divider()
            Row(
                modifier = Modifier.padding(Dimens.PADDING_5_DP),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color_light_black),
                        text = customer.customerName,
                    )
                    Text(
                        text = "Email: ${customer.email}", fontSize = 14.sp, color = Color.Gray
                    )
                    Text(
                        text = "Phone: ${customer.address.phone}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
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
                        TableCell(text = "Customer customerName:", weight = 3f)
                        TableCell(text = customer.customerName, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Email:", weight = 3f)
                        TableCell(text = customer.email.toString(), weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Street:", weight = 3f)
                        TableCell(text = customer.address.street, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "District:", weight = 3f)
                        TableCell(text = customer.address.district, weight = 7f)
                    }

                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "City:", weight = 3f)
                        TableCell(text = customer.address.city, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Postal Code:", weight = 3f)
                        TableCell(text = customer.address.postalCode, weight = 7f)
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .height(intrinsicSize = IntrinsicSize.Max)
                    ) {
                        TableCell(text = "Phone:", weight = 3f)
                        TableCell(text = customer.address.phone, weight = 7f)
                    }
                }
            }

        }
    }
    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false },
            title = { Text(text = "Thông báo") },
            text = { Text(text = "Bạn vừa nhấn giữ vào thẻ khách hàng.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Đóng")
                }
            })
    }

}

@Composable
fun RowScope.TableCell2(
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

val customer1 = Customer(
    idCustomer = "67276a79a0b1c2534dca6e31",
    customerName = "Tech Supplies Ltd.",
    email = "contact@techsupplies.com",
    address = Address(
        street = "789 Le Loi Street",
        district = "District 1",
        city = "Ho Chi Minh City",
        postalCode = "700005",
        phone = "0908123456"
    ),
)

//@Preview(showBackground = true)
//@Composable
//fun PreviewCustomerCard() {
//    CustomerCard(customer = customer1, onCardClick = { /* Handle card click here */ })
//}
