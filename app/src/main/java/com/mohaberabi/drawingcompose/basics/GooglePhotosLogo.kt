package com.mohaberabi.drawingcompose.basics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.drawingcompose.ui.theme.DrawingComposeTheme


@Composable
fun GooglePhotosLogo(modifier: Modifier = Modifier) {

    Canvas(
        modifier = modifier
            .padding(12.dp)
            .size(300.dp)

    ) {
        drawArc(
            color = Color.Blue,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(x = size.width / 2f, y = size.height * 0.25f),
            size = Size(size.width / 2f, height = size.height / 2f)
        )
        drawArc(
            color = Color.Red,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width / 2f, height = size.height / 2f),
            topLeft = Offset(x = size.width * .25f, y = 0f),
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width / 2f, height = size.height / 2f),
            topLeft = Offset(0f, y = size.height * 0.25f),
        )
        drawArc(
            color = Color.Yellow,
            startAngle = -90f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width / 2f, height = size.height / 2f),
            topLeft = Offset(size.width * 0.25f, y = size.height / 2f),
        )
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewGooglePhotos() {


    DrawingComposeTheme {
        GooglePhotosLogo()
    }
}