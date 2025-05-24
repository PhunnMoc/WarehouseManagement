package com.example.warehousemanagement.ui.feature.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.UploadImageButton
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import com.example.warehousemanagement.ui.feature.setting.viewModel.SettingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailSreen(
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val user by viewModel.informationUiState.collectAsStateWithLifecycle()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    var isExpanded by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(
                mainTitleText = "Detail Account",
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onNavigationBack()
                            })
                },
                scrollBehavior = scrollBehavior
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
                    firstName = temp.user.information?.firstName ?: ""
                    lastName = temp.user.information?.lastName ?: ""
                    email = temp.user.information?.email ?: ""
                    image = temp.user.information?.picture ?: ""

                    Box {
                        Image(
                            painter = rememberAsyncImagePainter(model = image),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = 8.dp)
                                .size(100.dp) // Thiết lập kích thước cho ảnh
                                .clip(CircleShape) // Cắt hình ảnh thành hình tròn
                                .border(
                                    width = 2.dp, // Độ dày của viền
                                    color = Color.Gray, // Màu sắc của viền
                                    shape = CircleShape // Hình dạng viền
                                ) // Khoảng cách bên trong nếu cần
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
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
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = image,
                            onValueChange = { image = it },
                            label = { Text("URL Image") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = temp.user.information?.role?.value ?:"",
                            onValueChange = {},
                            label = { Text("Role") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false // Disables editing, but retains the OutlinedTextField style
                        )
                        OutlinedTextField(
                            value = temp.user.username,
                            onValueChange = {},
                            label = { Text("Username") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false // Disables editing, but retains the OutlinedTextField style
                        )
                        var passwordVisible by remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = temp.user.passwordHash,
                            onValueChange = {},
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false, // Chỉ hiển thị, không cho chỉnh sửa
                            visualTransformation = if (passwordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                            trailingIcon = {
                                val icon =
                                    if (passwordVisible) Icons.Outlined.CheckCircle else Icons.Filled.CheckCircle
                                Icon(
                                    imageVector = icon,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                    modifier = Modifier.clickable {
                                        passwordVisible = !passwordVisible
                                    }
                                )
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun DialogAddAccount(
    onConfirm: (String, String, String) -> Unit, // Action when the confirm button is clicked, passing the input text
    onCancel: () -> Unit // Action when the cancel button is clicked
) {
    var inputText1 by rememberSaveable { mutableStateOf("") } // State for the input text
    var inputText2 by rememberSaveable { mutableStateOf("") } // State for the input text
    var inputText3 by rememberSaveable { mutableStateOf("") } // State for the input text

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
                    text = "Create worker account",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Message
                Text(
                    text = "ROLE : WORKER",
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(24.dp))

                // TextField for input
                OutlinedTextField(
                    value = inputText1,
                    onValueChange = { inputText1 = it },
                    label = { Text(text = "First name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = inputText2,
                    onValueChange = { inputText2 = it },
                    label = { Text(text = "Last name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = inputText3,
                    onValueChange = { inputText3 = it },
                    label = { Text(text = "Email") },
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
                    androidx.compose.material.Button(
                        onClick = onCancel,
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.background_gray)
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }

                    // Confirm Button
                    androidx.compose.material.Button(
                        onClick = {
                            onConfirm(
                                inputText1,
                                inputText2,
                                inputText3
                            ) // Pass the input text when confirming
                        },
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.background_theme)
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        Text(
                            text = "Create",
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }
    }
}