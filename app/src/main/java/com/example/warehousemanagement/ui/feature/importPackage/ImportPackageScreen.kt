package com.example.warehousemanagement.ui.feature.importPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.DialogWithInput
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPackageScreen(
    modifier: Modifier = Modifier,
    onClickAddProduct: (String, String) -> Unit,
    onClickAddProductByExcel: () -> Unit,
    onBackClick: () -> Unit,
    onClickSearch: () -> Unit,
    onNavigationDetailImportPackage: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isExpanded by remember { mutableStateOf(false) }
    var isShowDialog by remember { mutableStateOf(false) }
    var isFilter by remember {
        mutableStateOf(false)
    }

    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxSize()
            ) {
                if (isExpanded) {
                    Box(modifier = Modifier
                        .offset(x = 16.dp, y = 15.dp)
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { isExpanded = false })
                }

                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    AnimatedVisibility(visible = isExpanded) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp, end = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        Color.White, shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(text = "Add products", color = Color.Black)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            FloatingActionButton(
                                onClick = { isShowDialog = true },
                                containerColor = colorResource(id = R.color.background_gray)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Action 1")
                            }
                        }
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp, end = 16.dp)
                        ) {
                            // Thẻ Label cho nút FAB 2
                            Box(
                                modifier = Modifier
                                    .background(
                                        Color.White, shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(text = "Add products by excel", color = Color.Black)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            FloatingActionButton(
                                onClick = { onClickAddProductByExcel() },
                                containerColor = colorResource(id = R.color.background_gray)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Action 2")
                            }
                        }
                    }

                    FloatingActionButton(
                        modifier = Modifier.padding(bottom = 8.dp, end = 16.dp),
                        onClick = { isExpanded = !isExpanded },
                        containerColor = colorResource(id = R.color.background_theme)
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.Menu else Icons.Default.Add,
                            contentDescription = "Toggle FAB"
                        )
                    }
                }
            }
        },
        topBar = {
            HeaderOfScreen(
                mainTitleText = stringResource(id = R.string.screen_import_main_title),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackClick()
                            })
                },
                endContent = {},
                scrollBehavior = scrollBehavior
            )
        }) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Dimens.PADDING_16_DP),
            ) {
//                SearchBarPreview(
//                    modifier = Modifier.clickable {
//                        onClickSearch()
//                    }
//                )
                FilterAndSortButtons(onFilterClick = { isFilter = true }, onSortClick = {})
            }
            TabBarImport(onClickDetail = onNavigationDetailImportPackage)

        }
        if (isShowDialog) {
            DialogWithInput(
                title = stringResource(id = R.string.create_new_ip),
                message = stringResource(id = R.string.package_name_title),
                confirmText = "Create",
                cancelText = "Cancel",
                onConfirm = { packageName, note ->
                    isShowDialog = false
                    onClickAddProduct(packageName, note)
                },
                onCancel = {
                    isShowDialog = false
                },
            )
        }
    }
}


@Composable
fun TabBarImport(
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
            contentColor = colorResource(id = R.color.background_theme)
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

