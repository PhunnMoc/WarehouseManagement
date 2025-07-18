import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDismissState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.ui.common.DialogView
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.SearchBarPreview
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageLocationScreen(
    modifier: Modifier = Modifier,
    onNavigationBack: () -> Unit,
    onClickSearch: () -> Unit,
    onNavigationDetail: (String) -> Unit,

    viewModel: StorageLocationViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val roleUiState by viewModel.roleUiState.collectAsStateWithLifecycle()

    val warehouseAreas by viewModel.storageLocationUiState.collectAsStateWithLifecycle()
    var isExpanded by remember { mutableStateOf(false) }
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            if (roleUiState) {
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
                                // Thẻ Label cho nút FAB 1
                                Box(
                                    modifier = Modifier
                                        .background(
                                            Color.White, shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(text = "Add 1 item", color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                FloatingActionButton(
                                    onClick = { /* TODO: Action 1 */ },
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
                                    Text(text = "Add Items", color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                FloatingActionButton(
                                    onClick = { /* TODO: Action 2 */ },
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
            }
        },
        topBar = {
            HeaderOfScreen(
                mainTitleText = stringResource(id = R.string.screen_storage_location_main_title),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onNavigationBack()
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
                SearchBarPreview(enabled = false, modifier = Modifier.clickable {
                    onClickSearch()
                })
            }

            when (val storageLocation = warehouseAreas) {
                is StorageLocationUiState.Loading -> IndeterminateCircularIndicator()
                is StorageLocationUiState.Error -> NothingText()
                is StorageLocationUiState.Success -> {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(Dimens.PADDING_10_DP),
                        columns = GridCells.Adaptive(150.dp),
                    ) {
                        items(storageLocation.listStorageLocation) { area ->
                            WarehouseAreaCard(
                                area = area,
                                onConfirmDismiss = onNavigationDetail,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun WarehouseAreaCard(
    area: StorageLocation, onConfirmDismiss: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
            .clickable { onConfirmDismiss(area.id) },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    fontFamily = QuickSand,
                    text = "ID: ${area.id}",
                    //  color = colorResource(id = R.color.background_theme)
                )
                Text(
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_color_light_black),
                    text = "Name: ${area.storageLocationName}"
                )
            }
            Image(
                painter = rememberAsyncImagePainter(model = area.storageLocationImage),
                contentDescription = "",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWarehouseManagementScreen() {
//    WarehouseManagementTheme {
//        StorageLocationScreen(warehouseAreas = listLocation)
//    }
}
