package com.example.warehousemanagement.ui.feature.genre

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.feature.customer.CustomerData
import com.example.warehousemanagement.ui.feature.customer.viewModel.AddGenreViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormAddOrEditGenreForm(
    modifier: Modifier = Modifier,
    onSubmit: (CustomerData) -> Unit,
    onBackClick: () -> Unit,
    viewModel: AddGenreViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    // State variables for the genre form
    var genreName by rememberSaveable { mutableStateOf("") }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            HeaderOfScreen(
                modifier = modifier,
                mainTitleText = stringResource(id = R.string.screen_genre_main_title),
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
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = Dimens.PADDING_10_DP),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Genre name input
            OutlinedTextField(
                value = genreName,
                onValueChange = { genreName = it },
                label = { Text(text = stringResource(id = R.string.filter_tile_genre)) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimens.PADDING_10_DP)
            )

            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PADDING_10_DP),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BigButton(
                    modifier = Modifier.padding(vertical = Dimens.PADDING_10_DP),
                    enabled = genreName.isNotEmpty(),
                    labelname = "Submit",
                    onClick = {
                        viewModel.addNewGenre(
                            Genre(
                                idGenre = "",
                                genreName = genreName
                            )
                        )
                        Toast.makeText(context, "Genre added successfully!", Toast.LENGTH_SHORT).show()
                        onBackClick()
                    })
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewFormAddOrEditGenre() {
//    FormAddOrEditGenreForm(
//        onBackClick = {},
//
//    )
//}
