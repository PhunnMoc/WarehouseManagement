package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.domain.model.Product
import java.util.Date

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = product.productName, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: $${product.sellingPrice}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Quantity: ${product.quantity}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductCard(
        product = Product(
            idProduct = "1",
            productName = "Example Product",
            genreId = "Genre",
            quantity = 100,
            description = "This is an example product",
            importPrice = 50.0,
            sellingPrice = 100.0,
            supplierId = "Supplier",
            isInStock = true,
            barcode = "1234567890",
            storageLocationId = "Location",
            lastUpdated = Date(),
            image = null
        )
    )
}