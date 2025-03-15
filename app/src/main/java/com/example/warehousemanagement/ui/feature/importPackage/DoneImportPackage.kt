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

class DoneImportPackage {
}

@Composable
fun DoneImportPackage(
    modifier: Modifier = Modifier,
    importPackageDoneDoneUiState : ImportPackageUiState,
    onNavigationDetailImportPackages: (String) -> Unit,
) {

    when (importPackageDoneDoneUiState) {
        is ImportPackageUiState.Loading -> IndeterminateCircularIndicator()
        is ImportPackageUiState.Error -> NothingText()
        is ImportPackageUiState.Success -> {
            LazyColumn(modifier = modifier.padding(Dimens.PADDING_10_DP)) {
                items(importPackageDoneDoneUiState.importPackages) { importPackage ->
                    ImportPackageCard(
                        importPackage = importPackage,
                        onCardClick = {},
                        onLongPress = onNavigationDetailImportPackages,
                        onEditPendingPackage = {},
                    )
                    Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))
                }
            }
        }
    }

}