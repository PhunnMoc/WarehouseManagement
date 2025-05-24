package com.example.warehousemanagement.ui.feature.supplier

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.R
import com.example.warehousemanagement.ui.common.BigButton
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.supplier.viewModel.DetailSupplierUiState
import com.example.warehousemanagement.ui.feature.supplier.viewModel.DetailSupplierViewModel
import com.example.warehousemanagement.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSupplierScreen(
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
                mainTitleText = "Edit Supplier",
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

                var updateSupplier by remember {
                    mutableStateOf(detailSupplier.supplier)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.PADDING_10_DP),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Supplier Name
                    OutlinedTextField(
                        value = updateSupplier.name,
                        onValueChange = { updateSupplier = updateSupplier.copy(name = it) },
                        label = { Text("Supplier Name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Supplier Email
                    OutlinedTextField(
                        value = updateSupplier.email,
                        onValueChange = { updateSupplier = updateSupplier.copy(email = it) },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Address
                    OutlinedTextField(
                        value = "${updateSupplier.address.street}, ${detailSupplier.supplier.address.district}, ${detailSupplier.supplier.address.city}",
                        onValueChange = {},
                        label = { Text("Address") },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.PADDING_10_DP)
                    )

                    // Ratings
                    StarRatingUI(
                        modifier = Modifier.fillMaxWidth(),
                        rating = updateSupplier.ratings ?: 0,
                        enableEdit = true,
                        onRatingChanged = {
                            updateSupplier = updateSupplier.copy(ratings = it.toInt())
                        }
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    BigButton(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true,
                        labelname = "Update",
                        onClick = {
                            viewModel.updateNewSupplier(supplier = updateSupplier)
                            onBackClick()
                        })
                    // Back Button or Other Actions

                }
            }
        }
    }
}

@Composable
fun StarRatingUI(
    modifier: Modifier = Modifier,
    rating: Int,
    enableEdit: Boolean = false,
    onRatingChanged: (Int) -> Unit = {}
) {
    var currentRating by remember { mutableStateOf(rating) }

    Row(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = "Rate: "
        )
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star $i",
                tint = if (i <= currentRating) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(enabled = enableEdit) {
                        currentRating = i
                        onRatingChanged(i)
                    }
            )
        }
    }
}
