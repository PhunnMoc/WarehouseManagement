package com.example.warehousemanagement.ui.feature.login

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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.FunctionContainer
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.SearchBarWithSuggestion
import com.example.warehousemanagement.ui.common.WrapIcon
import com.example.warehousemanagement.ui.feature.home.HalfIcon
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.ImportPackageUiState
import com.example.warehousemanagement.ui.feature.login.viewModel.LoginUiState
import com.example.warehousemanagement.ui.feature.login.viewModel.LoginViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigationToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    Box(modifier = modifier.fillMaxSize()) {
        when (val temp = loginUiState) {
            is LoginUiState.Idle, LoginUiState.Error -> {
                Column(
                    modifier = modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // State để lưu trữ giá trị nhập vào và lỗi
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var emailError by remember { mutableStateOf("") }
                    var passwordError by remember { mutableStateOf("") }

                    // Hàm kiểm tra tính hợp lệ của email
                    fun isEmailValid(email: String): Boolean {
                        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    }

                    // Hàm kiểm tra tính hợp lệ của mật khẩu
                    fun isPasswordValid(password: String): Boolean {
                        // Kiểm tra mật khẩu dài ít nhất 6 ký tự
                        return password.length >= 6
                    }

                    // Giao diện màn hình đăng nhập
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = Dimens.PADDING_20_DP),
                            text = "WhereHow",
                            fontWeight = FontWeight.W600,
                            fontSize = 30.sp,
                            fontFamily = QuickSand,
                            color = colorResource(id = R.color.background_theme)
                        )
                        // Input email
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                emailError = if (isEmailValid(it)) "" else "Email không hợp lệ"
                            },
                            label = { Text("Email") },
                            isError = emailError.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                        // Hiển thị lỗi nếu có
                        if (emailError.isNotEmpty()) {
                            Text(
                                fontFamily = QuickSand,
                                text = emailError,
                                color = Color.Red,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Input mật khẩu
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError =
                                    if (isPasswordValid(it)) "" else "Mật khẩu phải có ít nhất 6 ký tự"
                            },
                            label = { Text("Password") },
                            isError = passwordError.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true
                        )
                        // Hiển thị lỗi nếu có
                        if (passwordError.isNotEmpty()) {
                            Text(
                                fontFamily = QuickSand,
                                text = passwordError,
                                color = Color.Red,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Nút đăng nhập
                        Button(
                            onClick = {
                                if (isEmailValid(email) && isPasswordValid(password)) {
                                    viewModel.login(email, password)
                                    println("Đăng nhập thành công")
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = isEmailValid(email) && isPasswordValid(password)
                        ) {
                            Text(
                                "Login",
                                fontFamily = QuickSand,
                            )
                        }
                    }
                }
            }

            is LoginUiState.Success -> {
                IndeterminateCircularIndicator()
            }

            is LoginUiState.Loading -> IndeterminateCircularIndicator()
        }
        HalfIcon(
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}
