package com.mohaberabi.drawingcompose.basics


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


@Composable
fun AppleWeatherLogo(modifier: Modifier = Modifier) {
    val colors = remember {
        listOf(
            Color(0xFF0084FF),
            Color(0xFF0084FF).copy(alpha = 0.4f),
        )

    }

    Canvas(
        modifier = modifier
            .padding(12.dp)
            .size(300.dp)

    ) {
        drawRoundRect(
            brush = Brush.verticalGradient(
                colors = colors,
                endY = size.height * 0.99f
            ),
            cornerRadius = CornerRadius(150f, 150f)
        )
        drawCircle(
            color = Color.Yellow,
            radius = size.width * 0.2f,
            center = Offset(x = size.height * 0.35f, y = size.height * 0.4f)
        )
        val path = Path().apply {
            moveTo(size.width * 0.45f, y = size.height * 0.75f)
            cubicTo(
                size.width * 0.25f,
                size.height * 0.75f,
                size.width * 0.25f,
                size.height * 0.45f,
                size.width * 0.45f,
                size.height * 0.5f,
            )
            cubicTo(
                size.width * 0.45f,
                size.height * 0.2f,
                size.width * 0.65f,
                size.height * 0.15f,
                size.width * 0.75f,
                size.height * 0.4f,
            )
            cubicTo(
                size.width * 0.95f,
                size.height * 0.35f,
                size.width * 0.95f,
                size.height * 0.75f,
                size.width * 0.75f,
                size.height * 0.75f,
            )
        }
        drawPath(
            path = path,
            color = Color.White.copy(alpha = 0.95f)
        )
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewAppleWeatherLogo() {


    DrawingComposeTheme {
        AppleWeatherLogo()
    }
}