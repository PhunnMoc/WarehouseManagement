package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.warehousemanagement.ui.common.ImportPackageCard
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.ImportPackageUiState
import com.example.warehousemanagement.ui.theme.Dimens

@Composable
fun PendingImportPackage(
    modifier: Modifier = Modifier,
    importPackagePendingUiState: ImportPackageUiState,
    onNavigationDetailImportPackages: (String) -> Unit,
    onNavigationEditImportPackages: (String) -> Unit,
    roleUiState: Boolean,
) {

    when (importPackagePendingUiState) {
        is ImportPackageUiState.Loading -> IndeterminateCircularIndicator()
        is ImportPackageUiState.Error -> NothingText()
        is ImportPackageUiState.Success -> {
            LazyColumn(modifier = modifier.padding(Dimens.PADDING_10_DP)) {
                items(importPackagePendingUiState.importPackages) { importPackage ->
                    ImportPackageCard(
                        importPackage = importPackage,
                        onCardClick = {},
                        onLongPress = onNavigationDetailImportPackages,
                        onEditPendingPackage = onNavigationEditImportPackages,
                        roleUiState = roleUiState,
                    )
                    Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))
                }
            }
        }
    }
}