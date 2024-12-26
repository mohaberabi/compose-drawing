package com.mohaberabi.drawingcompose.graph

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
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
fun DrawBarChart(
    modifier: Modifier = Modifier,
    barValues: List<Int> = listOf(),
    points: List<Float> = listOf(),
    interval: Int = 2,
    padding: Dp = 16.dp
) {
    val measurer = rememberTextMeasurer()
    val maxX = remember {
        barValues.max()
    }
    val maxY = remember {
        barValues.max()
    }
    val textSizes = remember {
        (0..maxX step interval).map {
            measurer.measure(
                text = "$it",
                style = TextStyle(fontSize = 8.sp)
            ).size
        }
    }
    val maxTextWidth = remember {
        textSizes.maxOf { it.width }
    }
    val maxTextHeight = remember {
        textSizes.maxOf { it.height }
    }
    Box(
        modifier = modifier
            .size(300.dp)
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width - maxTextWidth
            val height = size.height - maxTextHeight
            val xSpacing = width / maxX
            val ySpacing = height / maxY
            for (i in 0..maxX step interval) {
                drawText(
                    textMeasurer = measurer,
                    text = "$i",
                    style = TextStyle(fontSize = 8.sp),
                    topLeft = Offset(
                        x = 0f,
                        y = height - (ySpacing * i),
                    )
                )
            }
            for (i in 0..maxY step interval) {
                drawText(
                    textMeasurer = measurer,
                    text = "$i",
                    style = TextStyle(fontSize = 8.sp),
                    topLeft = Offset(
                        x = xSpacing * i,
                        y = height
                    )
                )
            }

            points.forEachIndexed { index, value ->
                val barHeight = (value / maxY) * height
                val top = height - barHeight
                val barWidth = (width / barValues.size) / 2

                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(
                        x = maxTextWidth + padding.value + ((barWidth * 2) * index),
                        y = top
                    ),
                    size = Size(barWidth, barHeight)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewBarChart(

) {

    DrawingComposeTheme {

        DrawBarChart(
            barValues = listOf(2, 8, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
            interval = 10,
            modifier = Modifier.size(300.dp),
            points = listOf(10f, 60f, 40f, 50f, 120f)
        )
    }
}

