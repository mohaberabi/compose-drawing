package com.mohaberabi.drawingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mohaberabi.drawingcompose.drawing.DrawScreen
import com.mohaberabi.drawingcompose.graph.BarChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarChart(
                data = listOf(),
            )
        }
    }
}

