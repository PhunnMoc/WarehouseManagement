package com.example.warehousemanagement.ui.feature.report.donutChart

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.warehousemanagement.ui.theme.QuickSand
import kotlin.math.pow
import kotlin.math.sqrt

private const val TOTAL_ANGLE = 360.0f
private val STROKE_SIZE_UNSELECTED = 40.dp
private val STROKE_SIZE_SELECTED = 60.dp

data class DonutChartData(
    val amount: Float,
    val color: Color,
    val title: String,
)

data class DonutChartDataCollection(
    var items: List<DonutChartData>
) {
    internal var totalAmount: Float = items.sumOf { it.amount.toDouble() }.toFloat()
        private set
}

private data class DrawingAngles(val start: Float, val end: Float)

private fun DrawingAngles.isInsideAngle(angle: Float) =
    angle > this.start && angle < this.start + this.end

private class DonutChartState(
    val state: State = State.Unselected
) {
    val stroke: Dp
        get() = when (state) {
            State.Selected -> STROKE_SIZE_SELECTED
            State.Unselected -> STROKE_SIZE_UNSELECTED
        }

    enum class State {
        Selected, Unselected
    }
}

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    chartSize: Dp = 350.dp,
    data: DonutChartDataCollection,
    gapPercentage: Float = 0.04f,
    selectionView: @Composable (selectedItem: DonutChartData?) -> Unit = {},
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val animationTargetState = (0..data.items.size).map {
        remember { mutableStateOf(DonutChartState()) }
    }
    val animValues = (0..data.items.size).map {
        animateDpAsState(
            targetValue = animationTargetState[it].value.stroke,
            animationSpec = TweenSpec(700)
        )
    }
    val anglesList: MutableList<DrawingAngles> = remember { mutableListOf() }
    val gapAngle = data.calculateGapAngle(gapPercentage)
    var center = Offset(0f, 0f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(chartSize)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { tapOffset ->
                            handleCanvasTap(
                                center = center,
                                tapOffset = tapOffset,
                                anglesList = anglesList,
                                currentSelectedIndex = selectedIndex,
                                currentStrokeValues = animationTargetState.map { it.value.stroke.toPx() },
                                onItemSelected = { index ->
                                    selectedIndex = index
                                    animationTargetState[index].value = DonutChartState(
                                        DonutChartState.State.Selected
                                    )
                                },
                                onItemDeselected = { index ->
                                    animationTargetState[index].value = DonutChartState(
                                        DonutChartState.State.Unselected
                                    )
                                },
                                onNoItemSelected = {
                                    selectedIndex = -1
                                }
                            )
                        }
                    )
                },
            onDraw = {
                val defaultStrokeWidth = STROKE_SIZE_UNSELECTED.toPx()
                center = this.center
                anglesList.clear()
                var lastAngle = 0f
                data.items.forEachIndexed { ind, item ->
                    val sweepAngle = data.findSweepAngle(ind, gapPercentage)
                    anglesList.add(DrawingAngles(lastAngle, sweepAngle))
                    val strokeWidth = animValues[ind].value.toPx()
                    drawArc(
                        color = item.color,
                        startAngle = lastAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(defaultStrokeWidth / 2, defaultStrokeWidth / 2),
                        style = Stroke(strokeWidth, cap = StrokeCap.Butt),
                        size = Size(
                            size.width - defaultStrokeWidth,
                            size.height - defaultStrokeWidth
                        )
                    )
                    lastAngle += sweepAngle + gapAngle
                }
            }
        )
        selectionView(if (selectedIndex >= 0) data.items[selectedIndex] else null)
    }
}

private fun handleCanvasTap(
    center: Offset,
    tapOffset: Offset,
    anglesList: List<DrawingAngles>,
    currentSelectedIndex: Int,
    currentStrokeValues: List<Float>,
    onItemSelected: (Int) -> Unit = {},
    onItemDeselected: (Int) -> Unit = {},
    onNoItemSelected: () -> Unit = {},
) {
    val normalized = tapOffset.findNormalizedPointFromTouch(center)
    val touchAngle =
        calculateTouchAngleAccordingToCanvas(center, normalized)
    val distance = findTouchDistanceFromCenter(center, normalized)

    var selectedIndex = -1
    var newDataTapped = false

    anglesList.forEachIndexed { ind, angle ->
        val stroke = currentStrokeValues[ind]
        if (angle.isInsideAngle(touchAngle)) {
            if (distance > (center.x - stroke) &&
                distance < (center.x)
            ) { // since it's a square center.x or center.y will be the same
                selectedIndex = ind
                newDataTapped = true
            }
        }
    }

    if (selectedIndex >= 0 && newDataTapped) {
        onItemSelected(selectedIndex)
    }
    if (currentSelectedIndex >= 0) {
        onItemDeselected(currentSelectedIndex)
        if (currentSelectedIndex == selectedIndex || !newDataTapped) {
            onNoItemSelected()
        }
    }
}

/**
 * Find the distance based on two points in a graph. Calculated using the pythagorean theorem.
 */
private fun findTouchDistanceFromCenter(center: Offset, touch: Offset) =
    sqrt((touch.x - center.x).pow(2) + (touch.y - center.y).pow(2))

/**
 * The touch point start from Canvas top left which ranges from (0,0) -> (canvas.width, canvas.height).
 * We need to normalize this point so that it's based on the canvas center instead.
 */
private fun Offset.findNormalizedPointFromTouch(canvasCenter: Offset) =
    Offset(this.x, canvasCenter.y + (canvasCenter.y - this.y))

/**
 * Calculate the touch angle based on the canvas center. Then adjust the angle so that
 * drawing starts from the 4th quadrant instead of the first.
 */
private fun calculateTouchAngleAccordingToCanvas(
    canvasCenter: Offset,
    normalizedPoint: Offset
): Float {
    val angle = calculateTouchAngleInDegrees(canvasCenter, normalizedPoint)
    return adjustAngleToCanvas(angle).toFloat()
}

/**
 * Calculate touch angle in radian using atan2(). Afterwards, convert the radian to degrees to be
 * compared to other data points.
 */
private fun calculateTouchAngleInDegrees(canvasCenter: Offset, normalizedPoint: Offset): Double {
    val touchInRadian = kotlin.math.atan2(
        normalizedPoint.y - canvasCenter.y,
        normalizedPoint.x - canvasCenter.x
    )
    return touchInRadian * -180 / Math.PI // Convert radians to angle in degrees
}

/**
 * Start from 4th quadrant going to 1st quadrant, degrees ranging from 0 to 360
 */
private fun adjustAngleToCanvas(angle: Double) = (angle + TOTAL_ANGLE) % TOTAL_ANGLE

/**
 * Calculate the gap width between the arcs based on [gapPercentage]. The percentage is applied
 * to the average count to determine the width in pixels.
 */
private fun DonutChartDataCollection.calculateGap(gapPercentage: Float): Float {
    if (this.items.isEmpty()) return 0f

    return (this.totalAmount / this.items.size) * gapPercentage
}

/**
 * Returns the total data points including the individual gap widths indicated by the
 * [gapPercentage].
 */
private fun DonutChartDataCollection.getTotalAmountWithGapIncluded(gapPercentage: Float): Float {
    val gap = this.calculateGap(gapPercentage)
    return this.totalAmount + (this.items.size * gap)
}

/**
 * Calculate the sweep angle of an arc including the gap as well. The gap is derived based
 * on [gapPercentage].
 */
private fun DonutChartDataCollection.calculateGapAngle(gapPercentage: Float): Float {
    val gap = this.calculateGap(gapPercentage)
    val totalAmountWithGap = this.getTotalAmountWithGapIncluded(gapPercentage)

    return (gap / totalAmountWithGap) * TOTAL_ANGLE
}

/**
 * Returns the sweep angle of a given point in the [DonutChartDataCollection]. This calculations
 * takes the gap between arcs into the account.
 */
private fun DonutChartDataCollection.findSweepAngle(
    index: Int,
    gapPercentage: Float
): Float {
    val amount = items[index].amount
    val gap = this.calculateGap(gapPercentage)
    val totalWithGap = getTotalAmountWithGapIncluded(gapPercentage)
    val gapAngle = this.calculateGapAngle(gapPercentage)
    return ((((amount + gap) / totalWithGap) * TOTAL_ANGLE)) - gapAngle
}

@Preview(showBackground = true)
@Composable
fun DonutChartPreview(
    modifier: Modifier = Modifier,
) {
    val viewData = DonutChartDataCollection(
        listOf(
            DonutChartData(1200.0f, getRandomColor(), title = "Food & Groceries"),
            DonutChartData(1500.0f, getRandomColor(), title = "Rent"),
            DonutChartData(300.0f, getRandomColor(), title = "Gas"),
            DonutChartData(700.0f, getRandomColor(), title = "Online Purchases"),
            DonutChartData(300.0f, getRandomColor(), title = "Clothing")
        )
    )
    DonutChart(modifier, data = viewData) { selected ->
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
