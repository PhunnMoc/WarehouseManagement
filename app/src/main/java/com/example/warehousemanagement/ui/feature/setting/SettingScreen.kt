package com.example.warehousemanagement.ui.feature.setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.UploadImageButton
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import com.example.warehousemanagement.ui.feature.setting.viewModel.SettingViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.customTextFieldColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
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
                mainTitleText = "",
                endContent = {
                    Text(
                        modifier = Modifier
                            .clickable { }
                            .padding(10.dp),
                        text = "Save")
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
                        UploadImageButton(
                            isShowPic = false,
                            modifier = Modifier
                                .zIndex(1f)
                                .align(BiasAlignment(horizontalBias = 1f, verticalBias = 1f)),
                            isText = false,
                            onImageSelected = { image = it ?: "" })
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
                            singleLine = true,
                            colors = customTextFieldColors(),
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = customTextFieldColors(),
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = customTextFieldColors(),
                        )
                        OutlinedTextField(
                            value = image,
                            onValueChange = { image = it },
                            label = { Text("URL Image") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = customTextFieldColors(),
                        )
                        OutlinedTextField(
                            value = temp.user.information?.role ?: "No role available",
                            onValueChange = {},
                            label = { Text("Role") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            enabled = false,
                            colors = customTextFieldColors(),
                        )
                    }

                }
            }

            Button(
                onClick = viewModel::logout,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_theme)), // Theme background color for the confirm button
                shape = RoundedCornerShape(50.dp), // Rounded corners for the confirm button
                modifier = Modifier
                    .width(95.2.dp)  // Width of the confirm button
                    .height(38.6.dp) // Height of the confirm button
            ) {
                // Text for the confirm button
                Text(
                    text = "Logout",
                    color = colorResource(id = R.color.text_color_black), // Black text color for the confirm button
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal
                ) // Font size for the confirm button
            }
        }
    }
}