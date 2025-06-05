package com.example.warehousemanagement.ui.feature.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.User
import com.example.warehousemanagement.ui.common.AIButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.common.SearchBarPreview
import com.example.warehousemanagement.ui.feature.chatBox.QuestionForChatBox
import com.example.warehousemanagement.ui.feature.user.viewModel.ManagerUserUiState
import com.example.warehousemanagement.ui.feature.user.viewModel.ManagerUserViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerUserScreen(
    onNavigateToChatBox: (String) -> Unit,
    onNavigationBack: () -> Unit,
    onNavigateToAddNewUser: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToUserDetail: (String) -> Unit,
    viewModel: ManagerUserViewModel = hiltViewModel(),
) {
    val userUiState by viewModel.managerUserUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    horizontalAlignment = Alignment.End,
                ) {

                    FloatingActionButton(
                        modifier = Modifier.padding(bottom = 8.dp, end = 16.dp),
                        onClick = onNavigateToAddNewUser,
                        containerColor = colorResource(id = R.color.background_theme)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Toggle FAB"
                        )
                    }
                }
            }
        },
        topBar = {
            HeaderOfScreen(
                mainTitleText = "Manager Account",
                startContent = {
                    Image(painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onNavigationBack()
                            })
                },
                endContent = {
                    AIButton(
                        onClick = { onNavigateToChatBox(QuestionForChatBox.ManagerAccount) },
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
                SearchBarPreview(
                    enabled = false,
                    modifier = Modifier.clickable {
                        //  onClickSearch()
                    }
                )
            }
            when (val users = userUiState) {
                is ManagerUserUiState.Loading -> IndeterminateCircularIndicator()
                is ManagerUserUiState.Error -> NothingText()
                is ManagerUserUiState.Success -> {
                    val groupedData = users.listUser.groupBy {
                        it.information?.firstName?.first()
                            ?: it.information?.lastName?.first()
                    }.toList().sortedBy { it.first }.toMap()
                    StickyHeaderList(
                        groupedData = groupedData,
                        onNavigateToUserDetail = onNavigateToUserDetail,
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaderList(
    groupedData: Map<Char?, List<User>>,
    onNavigateToUserDetail: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(Dimens.PADDING_20_DP)) {
        groupedData.forEach { (initial, persons) ->
            stickyHeader {
                HeaderItem(initial = initial?.uppercaseChar() ?: "Z".toCharArray().get(0))
            }
            items(persons) { person ->
                PersonItem(
                    person = person,
                    onNavigateToUserDetail = onNavigateToUserDetail
                )
            }
        }
    }
}

@Composable
fun HeaderItem(initial: Char) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(
            fontFamily = QuickSand,
            text = initial.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Composable
fun PersonItem(
    person: User,
    onNavigateToUserDetail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNavigateToUserDetail(person.idUser) }
    ) {
        Text(
            fontFamily = QuickSand,
            text = "${person.information?.firstName ?: ""} ${person.information?.lastName ?: ""}",
            //  style = MaterialTheme.typography.body1
        )
    }
}
