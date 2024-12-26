package com.mohaberabi.drawingcompose.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


data class PieData(
    val value: Float,
    val color: Color,
    val name: String,
)

@Composable
fun DrawPieChart(
    modifier: Modifier = Modifier,
    data: List<PieData> = listOf(),
) {


    val total = remember {
        data.map { it.value }.sum()
    }
    var startAngle = remember {
        -90f
    }
    Canvas(
        modifier = modifier
            .size(500.dp)
            .clipToBounds()
            .padding(16.dp),
    ) {
        val minSize = size.minDimension
        val radius = minSize / 2f

        data.fastForEach { pie ->

            val sweepAngle = (pie.value / total) * 360f
            drawArc(
                color = pie.color,
                sweepAngle = sweepAngle,
                useCenter = true,
                startAngle = startAngle,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(width = radius * 2, height = radius * 2)
            )

            startAngle += sweepAngle
        }
    }

}

@Composable
fun DrawPieArc(
    modifier: Modifier = Modifier,
    data: List<PieData> = listOf(),
) {


    val total = remember {
        data.map { it.value }.sum()
    }
    var startAngle = remember {
        -90f
    }
    Canvas(
        modifier = modifier
            .size(500.dp)
            .clipToBounds()
            .padding(16.dp),
    ) {


        val minSize = size.minDimension
        val radius = minSize / 2f

        data.fastForEach { pie ->

            val sweepAngle = (pie.value / total) * 360f
            drawArc(
                color = pie.color,
                sweepAngle = sweepAngle,
                useCenter = true,
                startAngle = startAngle,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(width = radius * 2, height = radius * 2)
            )

            startAngle += sweepAngle
        }
        drawCircle(
            color = Color.White,
            center = center,
            radius = 250f,
        )
    }

}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewDrawPieChart() {
    DrawingComposeTheme {

        DrawPieArc(
            data = listOf(
                PieData(12f, Color.Blue, "pizza"),
                PieData(14f, Color.Red, "salad"),
                PieData(9f, Color.Green, "sandwiches"),
                PieData(25f, Color.Black, "drink"),
            ),
        )
    }
}