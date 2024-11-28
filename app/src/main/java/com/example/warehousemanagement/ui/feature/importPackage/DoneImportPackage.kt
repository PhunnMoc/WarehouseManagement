package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.ui.common.ImportPackageCard
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.DoneImportPViewModel
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.ImportPackageUiState
import com.example.warehousemanagement.ui.feature.importPackage.viewModel.PendingImportPViewModel
import com.example.warehousemanagement.ui.theme.Dimens

class DoneImportPackage {
}

@Composable
fun DoneImportPackage(
    modifier: Modifier = Modifier,
    viewModel: DoneImportPViewModel = hiltViewModel(),
    onNavigationDetailImportPackages: (String) -> Unit,
) {
    val importPackageUiState by viewModel.importPackageUiState.collectAsStateWithLifecycle()

    when (val importPackage = importPackageUiState) {
        is ImportPackageUiState.Loading -> IndeterminateCircularIndicator()
        is ImportPackageUiState.Error -> NothingText()
        is ImportPackageUiState.Success -> {
            LazyColumn(modifier = modifier.padding(Dimens.PADDING_10_DP)) {
                items(importPackage.importPackages) { importPackage ->
                    ImportPackageCard(
                        importPackage = importPackage,
                        onCardClick = {},
                        onLongPress = onNavigationDetailImportPackages,
                    )
                    Spacer(modifier = Modifier.height(Dimens.PADDING_10_DP))
                }
            }
        }
    }

}