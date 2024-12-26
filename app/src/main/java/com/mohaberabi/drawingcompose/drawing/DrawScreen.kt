package com.mohaberabi.drawingcompose.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


data class DrawPath(val color: Color, val strokeWidth: Float, val path: Path = Path())

@Composable
fun DrawScreen(
    modifier: Modifier = Modifier,
) {
    val colors = remember {
        listOf(
            Color.Red,
            Color.Blue,
            Color.Yellow,
            Color.Green,
            Color.Gray
        )
    }


    var paths by remember { mutableStateOf(listOf<DrawPath>()) }
    var currentPath by remember { mutableStateOf(Path()) }
    var selectedColor by remember { mutableStateOf(colors[0]) }

    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f)
                    .clipToBounds()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragCancel = {

                                currentPath = Path()

                            },

                            onDrag = { change, _ ->
                                val position = change.position
                                currentPath.lineTo(position.x, position.y)
                                change.consume()
                            },

                            onDragEnd = {
                                paths = paths + DrawPath(
                                    path = currentPath,
                                    color = selectedColor,
                                    strokeWidth = 20f
                                )
                                currentPath = Path()
                            },

                            onDragStart = { offset ->
                                currentPath = Path().apply {
                                    moveTo(offset.x, offset.y)
                                }
                            }
                        )
                    },
            ) {


                paths.forEach {
                    drawPaintPath(it)
                }
                drawPath(
                    path = currentPath,
                    color = selectedColor,
                    style = Stroke(20f, cap = StrokeCap.Round)
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(
                    colors,
                ) { color ->
                    Box(
                        modifier = Modifier
                            .clickable { selectedColor = color }
                            .clip(CircleShape)
                            .background(color)
                            .size(50.dp)
                    )
                }
            }
        }
    }
}

fun DrawScope.drawPaintPath(drawPath: DrawPath) {
    drawPath(
        drawPath.path,
        color = drawPath.color,
        style = Stroke(width = drawPath.strokeWidth, cap = StrokeCap.Round)
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewDrawScreen() {
    DrawingComposeTheme {
        DrawScreen()
    }
}
