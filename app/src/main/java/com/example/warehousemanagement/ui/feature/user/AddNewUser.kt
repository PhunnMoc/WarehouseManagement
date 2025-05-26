package com.example.warehousemanagement.ui.feature.user

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.InputArea
import com.example.warehousemanagement.ui.feature.user.viewModel.AddNewUserViewModel
import com.example.warehousemanagement.ui.feature.user.viewModel.ManagerUserViewModel
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand
import com.example.warehousemanagement.ui.theme.customTextFieldColors
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewUser(
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddNewUserViewModel = hiltViewModel(),
) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }

    var role by remember {
        mutableStateOf("USER")
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(containerColor = colorResource(id = R.color.background_white),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HeaderOfScreen(mainTitleText = "Create new Account", startContent = {
                Image(painter = painterResource(id = R.drawable.icons8_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            onNavigationBack()
                        })
            }, endContent = {}, scrollBehavior = scrollBehavior
            )
        }) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {

            item {
                Column(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(vertical = 10.dp, horizontal = 15.dp),
                ) {
                    val date by remember {
                        mutableStateOf(
                            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        )
                    }
                    Text(
                        modifier = Modifier.padding(Dimens.PADDING_10_DP),
                        text = "Account",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    // Improt package name
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = {
                            Text(
                                fontFamily = QuickSand, text = "Email"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                        colors = customTextFieldColors(),
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                fontFamily = QuickSand, text = "Password"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                        colors = customTextFieldColors(),
                    )
                    OutlinedTextField(
                        value = date,
                        onValueChange = {},
                        label = {
                            Text(
                                fontFamily = QuickSand, text = "Date"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        colors = customTextFieldColors(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                    )

                    Text(
                        modifier = Modifier.padding(Dimens.PADDING_10_DP),
                        text = "Create an import package and another employee will check it before it is officially put into the warehouse.",
                        fontSize = 10.sp,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(innerpadding)
                        .padding(start = 2.dp, end = 2.dp, bottom = 20.dp, top = 2.dp)
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(vertical = 10.dp, horizontal = 15.dp),
                ) {

                    Text(
                        modifier = Modifier.padding(Dimens.PADDING_10_DP),
                        text = "User name information",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    // Improt package name
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = {
                            Text(
                                fontFamily = QuickSand, text = "First name"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                        colors = customTextFieldColors(),
                    )
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = {
                            Text(
                                fontFamily = QuickSand, text = "Last name"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP),
                        colors = customTextFieldColors(),
                    )
                    RoleDropdown(getRole = {
                        role = it
                    })


                    Spacer(modifier = Modifier.weight(1f))
                    BigButton(modifier = Modifier.fillMaxWidth(),
                        enabled = username.isNotBlank() && password.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank() && role.isNotBlank(),
                        labelname = "Create",
                        onClick = {
                            viewModel.addNewUser(
                                username = username,
                                password = password,
                                firstName = firstName,
                                lastName = lastName,
                                email = username,
                                role = role,
                                picture = "",
                            )
                            onNavigationBack()
                        })
                    // Back Button or Other Actions

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoleDropdown(
    getRole: (String) -> Unit,
) {
    val roles = listOf("USER", "ADMIN")
    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(roles[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedRole,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            label = {
                Text(
                    fontFamily = QuickSand, text = "Role"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(Dimens.PADDING_10_DP),
            colors = customTextFieldColors()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            roles.forEach { role ->
                DropdownMenuItem(onClick = {
                    selectedRole = role
                    expanded = false
                    getRole(role)
                }) {
                    Text(text = role)
                }
            }
        }
    }
}