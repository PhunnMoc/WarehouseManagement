package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Product
import java.util.Date

@Composable
fun ProductCard(product: Product) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.line_light_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .background(colorResource(id = R.color.icon_tint_white)),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.line_light_gray),
                        shape = RoundedCornerShape(10.dp)

                    ).background(colorResource(id = R.color.background_gray)),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,

            ){
                Image(
                    painter = painterResource(id = R.drawable.icons8_package), // Replace with your icon drawable resource
                    contentDescription = "Product Icon"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = product.idProduct,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.icons8_qr_32), // Replace with your QR code drawable resource
                    contentDescription = "QR Code",


                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = product.genreId,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp)


                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Quantity: ${product.quantity}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp)

                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Location: ${product.storageLocationId}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (product.isInStock) "Yes" else "No",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 12.sp,)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(
        product = Product(
            idProduct = "QUAT_001",
            productName = "Quat Senko",
            genreId = "Digital Devices",
            quantity = 100,
            description = "This is an example product",
            importPrice = 50.0,
            sellingPrice = 100.0,
            supplierId = "Supplier",
            isInStock = true,
            barcode = "1234567890",
            storageLocationId = "A2",
            lastUpdated = Date(),
            image = null
        )
    )
}