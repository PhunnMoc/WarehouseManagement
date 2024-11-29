package com.example.warehousemanagement.ui.feature.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.ImportPackageUiState
import com.example.warehousemanagement.ui.feature.setting.viewModel.InformationUiState
import com.example.warehousemanagement.ui.feature.setting.viewModel.SettingViewModel

@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    onLogout: () -> Unit = {}
) {
    val user by viewModel.informationUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {


        Spacer(modifier = Modifier.height(16.dp))

        when (val temp = user) {
            is InformationUiState.Loading -> IndeterminateCircularIndicator()
            is InformationUiState.Error -> NothingText()
            is InformationUiState.Success -> {
                Column {
                    Text(text = temp.user.information.firstName ?: "")
                    Text(text = temp.user.information.lastName ?: "")
                    Text(text = temp.user.information.email ?: "")
                    Text(text = temp.user.information.role ?: "")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút logout
        Button(
            onClick = {
                viewModel.logout()
                onLogout() // Đăng xuất, có thể chuyển hướng đến màn hình login
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Logout")
        }
    }
}
