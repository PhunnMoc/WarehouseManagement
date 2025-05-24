package com.example.warehousemanagement.ui.feature.supplier

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.supplier.viewModel.DetailSupplierUiState
import com.example.warehousemanagement.ui.feature.supplier.viewModel.DetailSupplierViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSupplier(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailSupplierViewModel = hiltViewModel(),
) {
    val detailSupplierUiState by viewModel.detailSupplierUiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        topBar = {
            HeaderOfScreen(
                modifier = modifier.padding(
                    top = Dimens.PADDING_20_DP,
                    start = Dimens.PADDING_20_DP,
                    end = Dimens.PADDING_20_DP,
                    bottom = Dimens.PADDING_10_DP
                ),
                mainTitleText = "Supplier Details",
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
        when (val detailSupplier = detailSupplierUiState) {
            is DetailSupplierUiState.Loading -> IndeterminateCircularIndicator()
            is DetailSupplierUiState.Error -> NothingText()
            is DetailSupplierUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.PADDING_10_DP),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Supplier Name
                    OutlinedTextField(
                        value = detailSupplier.supplier.name,
                        onValueChange = {},
                        label = { Text("Supplier Name") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Supplier Email
                    OutlinedTextField(
                        value = detailSupplier.supplier.email,
                        onValueChange = {},
                        label = { Text("Email") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Address
                    OutlinedTextField(
                        value = "${detailSupplier.supplier.address.street}, ${detailSupplier.supplier.address.district}, ${detailSupplier.supplier.address.city}",
                        onValueChange = {},
                        label = { Text("Address") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Ratings
                    StarRatingUI(
                        modifier = Modifier.fillMaxWidth(),
                        rating = detailSupplier.supplier.ratings ?: 0,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Back Button or Other Actions

                }
            }
        }
    }
}
