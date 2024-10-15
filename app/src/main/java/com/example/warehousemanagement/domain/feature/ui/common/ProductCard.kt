import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.runtime.setValue

@Composable
fun ProductCard(
    product: Product, // Add this parameter
    qrCodeIconRes: Int,
    onCardClick: () -> Unit// Image resource id for the QR code icon
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize()
            .clickable {
                isExpanded = !isExpanded
                onCardClick()
            },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_package),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                if (isExpanded) {
                    Row {
                        Column {
                            Column {
                                Text(
                                    text = "Product name:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = product.productName,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "Quantity:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = product.quantity.toString(),
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "Import Price:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "$${product.importPrice}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "Supplier ID:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = product.supplierId,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "In Stock:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = if (product.isInStock) "Yes" else "No",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Column {
                                Text(
                                    text = "Product ID:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(text = product.idProduct, fontSize = 14.sp, color = Color.Gray)
                            }
                            Column {
                                Text(
                                    text = "Genre ID:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(text = product.genreId, fontSize = 14.sp, color = Color.Gray)
                            }
                            Column {
                                Text(
                                    text = "Selling Price:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "$${product.sellingPrice}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "Location ID:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = product.storageLocationId,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            Column {
                                Text(
                                    text = "Last Updated:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "${product.lastUpdated}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }

                        }

                    }
                    Column {
                        Text(
                            text = "Description:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(text = product.description, fontSize = 14.sp, color = Color.Gray)
                    }
                } else {
                    Text(
                        text = product.productName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Id: ${product.idProduct}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Quantity: ${product.quantity}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = qrCodeIconRes),
                contentDescription = "QR Code Icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(
        product1,
        qrCodeIconRes = R.drawable.ic_qr_code,
        onCardClick = { /* Handle card click here */ }
    )
}
