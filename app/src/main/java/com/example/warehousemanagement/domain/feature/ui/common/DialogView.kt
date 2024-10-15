package com.example.warehousemanagement.domain.feature.ui.common
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.warehousemanagement.R
@Composable
fun DialogView(
    title: String, // Title of the dialog
    message: String, // Message of the dialog
    confirmText: String, // Text for the confirm button
    cancelText: String, // Text for the cancel button
    onConfirm: () -> Unit, // Action when confirm button is clicked
    onCancel: () -> Unit // Action when cancel button is clicked
) {
    // Dialog component
    Dialog(onDismissRequest = onCancel) {
        // Surface component for the dialog
        Surface(
            shape = RoundedCornerShape(25.dp), // Rounded corners for the dialog
            color = MaterialTheme.colorScheme.surface, // Color of the dialog surface
        ) {
            // Column for arranging the dialog components vertically
            Column(
                modifier = Modifier.padding(32.dp), // Padding around the column
                horizontalAlignment = Alignment.CenterHorizontally // Center the items horizontally
            ) {
                // Text component for the dialog title
                Text(text = title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), // Bold font weight for the title
                    textAlign = TextAlign.Center, // Center align the title
                    fontSize = 14.sp, // Font size for the title
                )
                Spacer(modifier = Modifier.height(32.dp)) // Space between the title and the message
                // Text component for the dialog message
                Text(text = message,
                    textAlign = TextAlign.Center, // Center align the message
                    fontSize = 13.sp) // Font size for the message
                Spacer(modifier = Modifier.height(32.dp)) // Space between the message and the buttons
                // Row for arranging the buttons horizontally
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly, // Evenly space the buttons
                    modifier = Modifier.fillMaxWidth() // Fill the maximum width
                ) {
                    // Cancel button
                    Button(onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_gray)), // Gray background color for the cancel button
                        shape = RoundedCornerShape(50.dp), // Rounded corners for the cancel button
                        modifier = Modifier
                            .width(95.2.dp)  // Width of the cancel button
                            .height(38.6.dp) // Height of the cancel button
                    ) {
                        // Text for the cancel button
                        Text(text = cancelText,
                            color = colorResource(id = R.color.text_color_black), // Black text color for the cancel button
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Normal)// Font size for the cancel button
                    }
                    // Confirm button
                    Button(onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_theme)), // Theme background color for the confirm button
                        shape = RoundedCornerShape(50.dp), // Rounded corners for the confirm button
                        modifier = Modifier
                            .width(95.2.dp)  // Width of the confirm button
                            .height(38.6.dp) // Height of the confirm button
                    ) {
                        // Text for the confirm button
                        Text(text = confirmText,
                            color = colorResource(id = R.color.text_color_black), // Black text color for the confirm button
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Normal) // Font size for the confirm button
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDialogView() {
    DialogView(
        title = "Are you sure?", // Preview title
        message = "Delete this product", // Preview message
        confirmText = "Confirm", // Preview confirm text
        cancelText = "Cancel", // Preview cancel text
        onConfirm = { /*TODO: Handle confirm action*/ }, // Preview confirm action
        onCancel = { /*TODO: Handle cancel action*/ } // Preview cancel action
    )
}