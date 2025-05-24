package com.example.warehousemanagement.ui.feature.exportPackage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.ui.common.ExportPackageCard
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.DoneExportPViewModel
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.ExportPackageUiState
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun DoneExportPackage(
    modifier: Modifier = Modifier,
    exportPackageDoneUiState: ExportPackageUiState,
    onNavigationDetailExportPackages: (String) -> Unit,
    onEditPendingPackage: (String) -> Unit,
) {

    when (exportPackageDoneUiState) {
        is ExportPackageUiState.Loading -> IndeterminateCircularIndicator()
        is ExportPackageUiState.Error -> NothingText()
        is ExportPackageUiState.Success -> {
            LazyColumn(modifier = modifier.padding(Dimens.PADDING_10_DP)) {
                items(exportPackageDoneUiState.exportPackages) { exportPackage ->
                    ExportPackageCard(
                        exportPackage = exportPackage,
                        onCardClick = {},
                        onLongPress = onNavigationDetailExportPackages,
                        onEditPendingPackage = onEditPendingPackage,
                    )
                    Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))
                }
            }
        }
    }
}