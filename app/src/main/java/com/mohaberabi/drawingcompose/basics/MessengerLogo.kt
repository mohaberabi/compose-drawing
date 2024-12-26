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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Fill
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
fun MessengerLogo(modifier: Modifier = Modifier) {
    val messengerColor = remember {
        Color(0xFF0084FF)
    }

    Canvas(
        modifier = modifier
            .padding(12.dp)
            .size(300.dp)
    ) {
        val triangle = Path().apply {
            moveTo(x = size.width * .2f, y = size.height * .84f)
            lineTo(x = size.width * .2f, y = size.height * .99f)
            lineTo(x = size.width * .3f, y = size.height * .91f)
        }
        val electric = Path().apply {
            moveTo(x = size.width * .2f, y = size.height * .6f)
            lineTo(x = size.width * .45f,y = size.height * .34f)
            lineTo(x = size.width * .55f,y = size.height * .45f)
            lineTo(x = size.width * .8f, y = size.height * .33f)
            lineTo(x = size.width * .55f,y = size.height * .6f)
            lineTo(x = size.width * .45f,y = size.height * .47f)
            close()
        }

        drawOval(
            color = messengerColor,
            size = Size(
                width = size.width,
                height = size.height * 0.95f
            )
        )
        drawPath(
            path = triangle,
            color = messengerColor,
            style = Fill
        )
        drawPath(
            path = electric,
            color = Color.White,
            style = Fill
        )
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewMessengerLogo() {
    DrawingComposeTheme {
        MessengerLogo()
    }
}