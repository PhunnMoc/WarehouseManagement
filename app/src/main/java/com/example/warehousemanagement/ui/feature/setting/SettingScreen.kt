package com.example.warehousemanagement.ui.feature.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import com.example.warehousemanagement.ui.feature.setting.viewModel.SettingViewModel
import com.example.warehousemanagement.ui.theme.Dimens



@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    onLogout: () -> Unit = {}
) {
    val user by viewModel.informationUiState.collectAsStateWithLifecycle()

    // State to hold editable user info
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Displaying loading or user data
        when (val temp = user) {
            is InformationUiState.Loading -> {
                IndeterminateCircularIndicator()
            }

            is InformationUiState.Error -> {
                NothingText() // You could add a specific error message here
            }

            is InformationUiState.Success -> {
                // Set initial values of text fields with user data
                firstName = temp.user.information.firstName ?: ""
                lastName = temp.user.information.lastName ?: ""
                email = temp.user.information.email ?: ""
                image = temp.user.information.picture ?: ""

                Image(
                    painter = rememberImagePainter(image.ifEmpty { R.drawable.customer }),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Editable User Info Fields
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = email ,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = image ,
                            onValueChange = { image = it },
                            label = { Text("URL Image") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = temp.user.information.role ?: "No role available",
                            onValueChange = {},
                            label = { Text("Role") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false // Disables editing, but retains the OutlinedTextField style
                        )
                    }
                }
            }
        }

        // Save Button (for now it only logs out, you can implement save functionality later)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.PADDING_10_DP),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
            verticalAlignment = Alignment.CenterVertically // Align vertically in the center
        ) {
            // Save Button
            BigButton(
                enabled = firstName.isNotEmpty() && email.isNotEmpty() && lastName.isNotEmpty(),
                labelname = "Save",
                onClick = {
                    // TODO: Implement saving logic here
                }
            )

            // Log out Button (always enabled)
            BigButton(
                enabled = true, // Always enabled
                labelname = "Out",
                onClick = {
                    onLogout()
                }
            )
        }


    }
}
