package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DetailImportViewModel
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun DetailDoneImportPackage(
    viewModel: DetailImportViewModel = hiltViewModel(),
    navigateToSetStorageLocationScreen: (String) -> Unit,
    onBack: () -> Unit,
) {
    CompositionLocalProvider(LocalTextStyle provides TextStyle(fontFamily = QuickSand)) {
        val detailImportUiState by viewModel.detailDoneImportPackageUiState.collectAsStateWithLifecycle()
        val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
        when (val detail = detailImportUiState) {
            is DetailImportUiState.Loading -> IndeterminateCircularIndicator()
            is DetailImportUiState.Error -> NothingText()
            is DetailImportUiState.Success -> {
                ImportPackage(
                    isPending = false,
                    user = currentUser,
                    importPackage = detail.detailImportPackage,
                    navigateToSetStorageLocationScreen = navigateToSetStorageLocationScreen,
                    onBack = onBack,
                    onUpdateImportPackage = viewModel::updateImportPackage
                )
            }
        }
    }
}