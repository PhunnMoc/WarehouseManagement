package com.example.warehousemanagement.ui.feature.report

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warehousemanagement.ui.common.IndeterminateCircularIndicator
import com.example.warehousemanagement.ui.common.NothingText
import com.example.warehousemanagement.ui.feature.genre.viewModel.GenreUiState
import com.example.warehousemanagement.ui.feature.genre.viewModel.GenreViewModel
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChart
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartData
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartDataCollection
import com.example.warehousemanagement.ui.feature.report.donutChart.DonutChartPreview
import com.example.warehousemanagement.ui.feature.report.donutChart.getRandomColor
import com.example.warehousemanagement.ui.feature.report.donutChart.toMoneyFormat
import com.example.warehousemanagement.ui.feature.report.list.CardGenre
import com.example.warehousemanagement.ui.feature.storage.viewModel.StorageLocationUiState
import com.example.warehousemanagement.ui.theme.Dimens
import com.example.warehousemanagement.ui.theme.QuickSand

@Composable
fun InventoryScreen(
    genreViewModel: GenreViewModel = hiltViewModel(),
) {
    val genreUiState by genreViewModel.genreUiState.collectAsStateWithLifecycle()

    when (val genres = genreUiState) {
        is GenreUiState.Loading -> IndeterminateCircularIndicator()
        is GenreUiState.Error -> NothingText()
        is GenreUiState.Success -> {

            val min = 5.0f
            val max = 10.0f
            val randomFloatInRange = min + kotlin.random.Random.nextFloat() * (max - min)
            val viewData = DonutChartDataCollection(genres.listGenre.mapIndexed { index, genre ->
                DonutChartData(
                    amount = randomFloatInRange * index,
                    color = getRandomColor(),
                    title = genre.genreName,
                )
            })

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    DonutChart(data = viewData) { selected ->
                        AnimatedContent(targetState = selected, label = "") {
                            val amount = it?.amount ?: viewData.totalAmount
                            val text = it?.title ?: "Total"

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "$${amount.toMoneyFormat(true)}")
                                Text(text = text, fontFamily = QuickSand)
                            }
                        }
                    }
                }
                itemsIndexed(genres.listGenre) { index, it ->
//                        CardGenre(
//                            genre = it,
//                            moneyInStock = viewData.items[index].amount
//                        )
                }
            }

        }
    }
}



