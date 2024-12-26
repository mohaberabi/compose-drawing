package com.mohaberabi.drawingcompose.basics

import android.media.FaceDetector.Face
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
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


@Composable
fun FacebookLogo(
    modifier: Modifier = Modifier,
) {


    val facebookColor = remember {
        Color.Blue
    }
    val paint = remember {
        android.graphics.Paint().apply {
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = 1000f
            color = Color.White.toArgb()
        }
    }
    Canvas(
        modifier = modifier
            .size(300.dp)
            .padding(12.dp),
    ) {
        drawRoundRect(
            color = facebookColor,
            cornerRadius = CornerRadius(
                x = 120f,
                y = 120f,
            ),
        )
        drawContext.canvas.nativeCanvas.drawText(
            "f",
            center.x + 40f,
            center.y + 380f,
            paint
        )
    }
}


@Preview
@Composable
private fun PreviewFacebookLogo() {


    DrawingComposeTheme {
        FacebookLogo()
    }
}