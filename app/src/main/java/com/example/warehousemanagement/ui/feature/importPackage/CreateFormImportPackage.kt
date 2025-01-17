package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun CreateFormImportPackage() {

}

@Composable
fun TabBarCreateFormImport(
    onClickDetail: (String) -> Unit,
) {
    val tabs = listOf(
        stringResource(id = R.string.pending_import_title),
        stringResource(id = R.string.done_import_title)
    ) // Danh sách các tab
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        // TabRow để hiển thị các tab
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            contentColor = colorResource(id = R.color.text_color_black),
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontFamily = QuickSand
                        )
                    })
            }
        }

        // Hiển thị nội dung của tab tương ứng
        when (selectedTabIndex) {
            0 -> {
                PendingImportPackage(
                    onNavigationDetailImportPackages = onClickDetail,
                )
            }

            1 -> {
                DoneImportPackage(
                    onNavigationDetailImportPackages = onClickDetail,
                )
            }
        }
    }
}

