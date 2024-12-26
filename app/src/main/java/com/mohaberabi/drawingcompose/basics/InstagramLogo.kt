package com.mohaberabi.drawingcompose.basics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


@Composable
fun InstagramLogo(modifier: Modifier = Modifier) {
    val colors = remember {
        listOf(
            Color(0xFFFD1D1D),
            Color(0xFF833AB4),
            Color.Blue,
        )
    }
    Canvas(
        modifier = modifier
            .size(300.dp)
            .padding(12.dp),
    ) {
        drawRoundRect(
            brush = Brush.linearGradient(
                colors,
            ),
            cornerRadius = CornerRadius(
                x = 120f,
                y = 120f,
            ),
            style = Stroke(width = 40f)
        )

        drawCircle(
            Brush.linearGradient(colors = colors),
            radius = 165f,
            style = Stroke(width = 56f)
        )
        drawCircle(
            Brush.linearGradient(colors = colors),
            radius = 30f,
            center = Offset(x = size.width * 0.8f, y = size.height*0.2f)
        )
    }
}


@Preview(showBackground = true,)
@Composable
private fun PreviewInstagramLogo() {
    DrawingComposeTheme {
        InstagramLogo()
    }
}