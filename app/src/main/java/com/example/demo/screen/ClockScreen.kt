package com.example.demo.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Clock(Modifier.fillMaxSize(0.7f))
    }
}

@Composable
fun Clock(modifier: Modifier = Modifier) {

    var currentTime by remember { mutableStateOf(Calendar.getInstance()) }
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Calendar.getInstance()
            delay(16)
        }
    }
    val hour = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)
    val second = currentTime.get(Calendar.SECOND)
    val millis = currentTime.get(Calendar.MILLISECOND)

    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val center = Offset(size.width / 2, size.height / 2)

        drawCircle(color = Color(0x321E1F22), radius = radius, center = center)
        drawCircle(
            color = Color.Black,
            radius = radius,
            center = center,
            style = Stroke(width = 3f)
        )
        drawCircle(color = Color.Black, radius = 8f, center = center)

        for (i in 0 until 60) {
            val angle = Math.toRadians((i * 6).toDouble()).toFloat()
            val lineLength = if (i % 5 == 0) 12f else 6f
            val start = Offset(
                x = center.x + (radius - lineLength) * cos(angle),
                y = center.y + (radius - lineLength) * sin(angle)
            )
            val end = Offset(
                x = center.x + radius * cos(angle),
                y = center.y + radius * sin(angle)
            )
            drawLine(
                color = Color.Black,
                start = start,
                end = end,
                strokeWidth = if (i % 5 == 0) 5f else 3f
            )
        }

        val hourAngle = Math.toRadians(((hour + minute / 60f) * 30 - 90).toDouble())
        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(
                x = center.x + radius * 0.4f * cos(hourAngle).toFloat(),
                y = center.y + radius * 0.4f * sin(hourAngle).toFloat()
            ),
            strokeWidth = 8f
        )

        val minuteAngle = Math.toRadians((minute * 6 - 90).toDouble())
        drawLine(
            color = Color.DarkGray,
            start = center,
            end = Offset(
                x = center.x + radius * 0.6f * cos(minuteAngle).toFloat(),
                y = center.y + radius * 0.6f * sin(minuteAngle).toFloat()
            ),
            strokeWidth = 6f
        )

        val angleRad = Math.toRadians(((second + millis / 1000f) * 6f - 90).toDouble())
        drawLine(
            color = Color.Red,
            start = center,
            end = Offset(
                x = center.x + radius * 0.8f * cos(angleRad).toFloat(),
                y = center.y + radius * 0.8f * sin(angleRad).toFloat()
            ),
            strokeWidth = 3f
        )
    }
}