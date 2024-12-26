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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme
import kotlin.math.abs

data class PathData(
    val id: String,
    val color: Color,
    val path: List<Offset>
)


@Composable
fun FlexibleDrawScreen(
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


    var paths by remember { mutableStateOf(listOf<PathData>()) }
    var currentPath by remember { mutableStateOf<PathData?>(null) }
    var selectedColor by remember { mutableStateOf(colors[0]) }

    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clipToBounds()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragCancel = {

                                currentPath = null

                            },

                            onDrag = { change, _ ->
                                val offset = change.position
                                currentPath?.let {
                                    currentPath = it.copy(
                                        path = it.path + offset,
                                    )
                                }

                            },

                            onDragEnd = {


                                currentPath?.let {
                                    paths += it
                                }
                                currentPath = null
                            },

                            onDragStart = { offset ->

                                currentPath = PathData(
                                    color = selectedColor,
                                    id = "${offset.x + offset.y}",
                                    path = listOf()
                                )
                            }
                        )
                    },
            ) {

                paths.fastForEach {
                    drawPathBezier(
                        path = it.path,
                        color = it.color,
                    )
                }
                currentPath?.let {
                    drawPathBezier(
                        path = it.path,
                        color = it.color,
                    )
                }
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


private fun DrawScope.drawPathBezier(
    path: List<Offset>,
    color: Color,
    thickness: Float = 10f
) {
    val smoothness = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)
            val smooth = 5

            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx >= smooth || dy >= smooth) {
                    quadraticBezierTo(
                        x1 = (from.x + to.x) / 2,
                        y1 = (from.y + to.y) / 2,
                        x2 = to.x,
                        y2 = to.y
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothness,
        color = color,
        style = Stroke(
            width = thickness,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewFlexibleDrawScreen() {
    DrawingComposeTheme {
        FlexibleDrawScreen()
    }
}
