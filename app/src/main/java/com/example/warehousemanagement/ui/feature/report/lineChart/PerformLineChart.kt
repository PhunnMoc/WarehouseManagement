package com.example.warehousemanagement.ui.feature.report.lineChart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.ui.feature.report.donutChart.getRandomColor

private fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
    (value - min) / (max - min)

val months = listOf<String>(
    "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec",
)

@Composable
fun PerformanceChart(
    max: Float,
    min: Float,
    modifier: Modifier = Modifier,
    list: List<Float>,
    lineColor: Color,
) {
    val zipList: List<Pair<Float, Float>> = list.zipWithNext()

    Row(modifier = modifier.height(200.dp)) {

        zipList.forEachIndexed { index, pair ->
            val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
            val toValuePercentage = getValuePercentageForRange(pair.second, max, min)
            val textMeasurer = rememberTextMeasurer()

            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onDraw = {
                    val heightDrawScope = size.height - 20.dp.toPx()
                    val fromPoint = Offset(
                        x = 0f,
                        y = heightDrawScope.times(1 - fromValuePercentage)
                    )
                    val toPoint = Offset(
                        x = size.width,
                        y = heightDrawScope.times(1 - toValuePercentage)
                    )
                    drawLine(
                        color = Color(0xFFE3E3E3),
                        start = Offset(
                            0f, 0f
                        ),
                        end = Offset(
                            0f, heightDrawScope
                        ),
                        strokeWidth = 3f
                    )

                    drawLine(
                        color = lineColor,
                        start = fromPoint,
                        end = toPoint,
                        strokeWidth = 3f
                    )


                    // Draw month label at the center of each segment
                    val text = months[index]
                    val textLayoutResult = textMeasurer.measure(text)
                    val textWidth = textLayoutResult.size.width

                    drawText(
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                        textMeasurer = textMeasurer,
                        text = text,
                        topLeft = Offset(
                            x = 0f - textWidth / 2, // Căn giữa text
                            y = size.height - 10.dp.toPx()
                        )
                    )
                    if (index == months.size - 2) {
                        drawText(
                            style = TextStyle(
                                fontSize = 10.sp,
                            ),
                            textMeasurer = textMeasurer,
                            text = "Dec",
                            topLeft = Offset(
                                x = size.width - textWidth, // Căn giữa text
                                y = size.height - 10.dp.toPx()
                            )
                        )
                        drawLine(
                            color = Color(0xFFE3E3E3),
                            start = Offset(
                                size.width, 0f
                            ),
                            end = Offset(
                                size.width, heightDrawScope
                            ),
                            strokeWidth = 3f
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    //  PerformanceChart()
}