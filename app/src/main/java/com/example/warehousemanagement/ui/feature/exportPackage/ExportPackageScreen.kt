package com.example.warehousemanagement.ui.feature.exportPackage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.ui.common.AIButton
import com.example.warehousemanagement.ui.common.CustomerCard
import com.example.warehousemanagement.ui.common.DialogWithInput
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.chatBox.QuestionForChatBox
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.ExportPackageUiState
import com.example.warehousemanagement.ui.feature.exportPackage.viewModel.ExportPackageViewMode
import com.example.warehousemanagement.ui.feature.search.viewModel.SearchCustomerUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportPackageScreen(
    modifier: Modifier = Modifier,
    onClickAddExportForm: () -> Unit,
    onClickAddProductByExcel: () -> Unit,
    onBackClick: () -> Unit,
    onNavigationDetailExportPackage: (String) -> Unit,
    onEditPendingPackage: (String) -> Unit,
    onNavigateToChatBox: (String) -> Unit,
    viewModel: ExportPackageViewMode = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isExpanded by remember { mutableStateOf(false) }

    val exportPackagePendingUiState by viewModel.exportPackagePendingUiState.collectAsState()
    val exportPackageDoneUiState by viewModel.exportPackageDoneUiState.collectAsState()

    val roleUiState by viewModel.roleUiState.collectAsStateWithLifecycle()
    val customerUiState by viewModel.customerUiState.collectAsStateWithLifecycle()
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
                                onClick = onClickAddExportForm,
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
                mainTitleText = stringResource(id = R.string.screen_export_main_title),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackClick()
                            })
                },
                endContent = {
                    AIButton(
                        onClick = { onNavigateToChatBox(QuestionForChatBox.ExportPackage) },
                    )
                },
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
                //               FilterAndSortButtons(onFilterClick = { isFilter = true }, onSortClick = {})
            }
            TabBarExport(
                roleUiState = roleUiState,
                onEditPendingPackage = onEditPendingPackage,
                exportPackagePendingUiState = exportPackagePendingUiState,
                exportPackageDoneUiState = exportPackageDoneUiState,
                onNavigationDetailExportPackage = onNavigationDetailExportPackage
            )
        }
    }
}

@Composable
fun TabBarExport(
    roleUiState: Boolean,
    onNavigationDetailExportPackage: (String) -> Unit,
    onEditPendingPackage: (String) -> Unit,
    exportPackagePendingUiState: ExportPackageUiState,
    exportPackageDoneUiState: ExportPackageUiState,
) {
    val tabs = listOf(
        stringResource(id = R.string.pending_import_title),
        stringResource(id = R.string.done_import_title)
    ) // Danh sách các tab
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.line_light_gray))
    ) {
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


        when (selectedTabIndex) {
            0 -> {
                PendingExportPackage(
                    roleUiState = roleUiState,
                    onEditPendingPackage = onEditPendingPackage,
                    exportPackagePendingUiState = exportPackagePendingUiState,
                    onNavigationDetailExportPackages = onNavigationDetailExportPackage,
                )
            }

            1 -> {
                DoneExportPackage(
                    roleUiState = roleUiState,
                    exportPackageDoneUiState = exportPackageDoneUiState,
                    onNavigationDetailExportPackages = onNavigationDetailExportPackage,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DialogAddExportPackage(
    title: String, // Title of the dialog
    message: String, // Message of the dialog
    confirmText: String, // Text for the confirm button (e.g., "Create")
    cancelText: String, // Text for the cancel button
    customerUiState: Customer?,
    onConfirm: (packageName: String, note: String, customerId: String) -> Unit, // Action when the confirm button is clicked, passing the input text
    onChangCustomerIdQuery: (String) -> Unit,
    onCancel: () -> Unit, // Action when the cancel button is clicked
) {
    var inputText1 by rememberSaveable { mutableStateOf("") } // State for the input text
    var inputText2 by rememberSaveable { mutableStateOf("") } // State for the input text
    var customerId by remember { mutableStateOf("") }


    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = RoundedCornerShape(25.dp), color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                androidx.compose.material3.Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Message
                androidx.compose.material3.Text(
                    text = message, textAlign = TextAlign.Center, fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(24.dp))

                // TextField for input
                OutlinedTextField(
                    value = inputText1,
                    onValueChange = { inputText1 = it },
                    label = { androidx.compose.material3.Text(text = "Package Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = inputText2,
                    onValueChange = { inputText2 = it },
                    label = { androidx.compose.material3.Text(text = "Note") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = customerId,
                    onValueChange = {
                        customerId = it
                        onChangCustomerIdQuery(it)
                        println("WTF $it")
                    },
                    label = { androidx.compose.material3.Text(text = "Customer Id") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                //supplier
                if (customerUiState != null) {
                    CustomerCard(customer = customerUiState,
                        onCardClick = { /*TODO*/ },
                        onLongPress = {},
                        roleUiState = false,
                        onEditCustomer = {}
                    )
                }

                // Buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Cancel Button
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_gray)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        androidx.compose.material3.Text(
                            text = cancelText,
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }

                    // Confirm Button
                    Button(
                        onClick = {
                            onConfirm(
                                inputText1, inputText2, customerId
                            ) // Pass the input text when confirming
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.background_theme)),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .width(95.2.dp)
                            .height(38.6.dp)
                    ) {
                        androidx.compose.material3.Text(
                            text = confirmText,
                            color = colorResource(id = R.color.text_color_black),
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }
    }
}
