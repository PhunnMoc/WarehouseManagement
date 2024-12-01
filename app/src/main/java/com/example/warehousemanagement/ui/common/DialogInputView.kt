package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.warehousemanagement.R

@Composable
fun DialogWithInput(
    title: String, // Title of the dialog
    message: String, // Message of the dialog
    confirmText: String, // Text for the confirm button (e.g., "Create")
    cancelText: String, // Text for the cancel button
    onConfirm: (String, String) -> Unit, // Action when the confirm button is clicked, passing the input text
    onCancel: () -> Unit // Action when the cancel button is clicked
) {
    var inputText1 by rememberSaveable { mutableStateOf("") } // State for the input text
    var inputText2 by rememberSaveable { mutableStateOf("") } // State for the input text

    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Message
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(24.dp))

                // TextField for input
                OutlinedTextField(
                    value = inputText1,
                    onValueChange = { inputText1 = it },
                    label = { Text(text = "Package Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = inputText2,
                    onValueChange = { inputText2 = it },
                    label = { Text(text = "Note") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                // Buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Cancel Button
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_gray)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(
                            text = cancelText,
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }

                    // Confirm Button
                    Button(
                        onClick = {
                            onConfirm(inputText1, inputText2) // Pass the input text when confirming
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_theme)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(
                            text = confirmText,
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }
    }
}
