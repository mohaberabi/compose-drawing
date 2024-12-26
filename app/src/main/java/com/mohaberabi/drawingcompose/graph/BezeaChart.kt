package com.mohaberabi.drawingcompose.graph

import android.graphics.Point
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


@Composable
fun BezierChart(

    modifier: Modifier = Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    interval: Int = 10,
    padding: Dp = 16.dp
) {

    val measurer = rememberTextMeasurer()

    val maxX = remember {
        xValues.maxOrNull() ?: 0
    }
    val maxY = remember {
        yValues.maxOrNull() ?: 0
    }


    val textSizes = remember {
        (0..maxX step interval).map {
            measurer.measure(
                text = if (it == 0) "" else "$it",
                style = TextStyle(fontSize = 12.sp)
            ).size
        }
    }
    val maxTextWidth = remember {
        textSizes.maxOf { it.width }
    }
    val maxTextHeight = remember {
        textSizes.maxOf { it.height }
    }
    val coordinates = remember {
        mutableListOf<PointF>()
    }
    Box(
        modifier = modifier.padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            val width = size.width - (padding.value + maxTextWidth)
            val height = size.height - (padding.value + maxTextHeight)
            val xSpacing = if (maxX == 0) 0f else width / maxX
            val ySpacing = if (maxY == 0) 0f else height / maxY

            for (index in 0..maxX step interval) {
                drawText(
                    textMeasurer = measurer,
                    text = "$index",
                    style = TextStyle(fontSize = 12.sp),
                    topLeft = Offset(
                        y = height,
                        x = xSpacing * index
                    )
                )
            }
            for (index in 0..maxY step interval) {
                drawText(
                    textMeasurer = measurer,
                    text = "$index",
                    style = TextStyle(fontSize = 12.sp),
                    topLeft = Offset(
                        y = height - (ySpacing * index),
                        x = 0f,
                    )
                )
            }
            points.fastForEachIndexed { index, value ->
                val x = xSpacing * xValues[index]
                val y = height - (ySpacing * value)
                coordinates.add(PointF(x, y))
                drawCircle(
                    color = Color.Green,
                    radius = 10f,
                    center = Offset(
                        x = x,
                        y = y,
                    ),
                )
            }

            val bezeirPoints = getBezierPoints(coordinates)
            val path = Path().apply {
                reset()
                moveTo(
                    coordinates.first().x,
                    coordinates.first().y
                )
                for (index in 1 until coordinates.size) {
                    val bezear = bezeirPoints[index - 1]
                    cubicTo(
                        x1 = bezear.first.x,
                        y1 = bezear.first.y,
                        x2 = bezear.second.x,
                        y2 = bezear.second.y,
                        x3 = coordinates[index].x,
                        y3 = coordinates[index].y
                    )
                }
            }
            drawPath(
                path = path,
                color = Color.Green.copy(alpha = 0.2f),
                style = Stroke(12f)
            )
        }
    }
}

fun getBezierPoints(
    points: List<PointF>
): List<Pair<PointF, PointF>> {
    val res = mutableListOf<Pair<PointF, PointF>>()
    for (i in 1 until points.size) {
        val prev = points[i - 1]
        val curr = points[i]
        val next = points.getOrNull(i + 1)

        val c1 = PointF(
            prev.x + (curr.x - prev.x) / 3f,
            prev.y + (curr.y - prev.y) / 3,
        )
        val c2 = next?.let {
            PointF(
                curr.x - (it.x - prev.x) / 3f,
                curr.y - (it.y - prev.y) / 3,
            )
        } ?: PointF(
            prev.x - (curr.x - prev.x) / 3f,
            prev.y - (curr.y - prev.y) / 3f,
        )
        res.add(c1 to c2)
    }
    return res
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewBezeirChart() {
    DrawingComposeTheme {

        BezierChart(
            yValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
            xValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
            interval = 10,
            modifier = Modifier.size(500.dp),
            points = listOf(0f, 5.4f, 2f, 6f, 9f, 4f, 8f, 1f, 11f, 3f).map { it * 10 }
        )
    }
}