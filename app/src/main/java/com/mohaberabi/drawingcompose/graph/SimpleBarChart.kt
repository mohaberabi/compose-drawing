package com.mohaberabi.drawingcompose.graph


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import kotlin.math.roundToInt

@Composable
fun BarChart(
    data: List<Float>,
    interval: Int = 5,
) {
    val measurer = rememberTextMeasurer()

    Box(
        modifier = Modifier
            .size(300.dp)
            .padding(16.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val max = data.max()
            val barWidth = size.width / (data.size * 2)
            val maxBarHeight = size.height
            val ySpacing = size.height / data.size
            for (i in 0..max.toInt() step interval) {
                val text = "$i"
                val textSize = measurer.measure(text)
                drawText(
                    textMeasurer = measurer,
                    text = text,
                    style = TextStyle(fontSize = 8.sp),
                    topLeft = Offset(
                        x = 0f,
                        y = size.height - (30 * i)
                    )
                )
            }
            data.forEachIndexed { index, value ->
                val left = index * (barWidth * 2)
                val top = maxBarHeight - (value / max) * maxBarHeight
                val bottom = maxBarHeight
                drawRect(
                    color = Color.Blue,
                    topLeft = androidx.compose.ui.geometry.Offset(left, top),
                    size = androidx.compose.ui.geometry.Size(barWidth, bottom - top)
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun BarChartPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BarChart(
            listOf(
                10f, 20f, 15f, 30f, 25f,
            )
        )
    }
}