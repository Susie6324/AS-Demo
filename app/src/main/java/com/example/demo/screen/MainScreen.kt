package com.example.demo.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SwitchClock()
    }
}

@Composable
fun SwitchClock() {
    var show24 by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DigitalClock(show24 = show24)
        Spacer(modifier = Modifier.height(2.dp))
        Button(onClick = { show24 = !show24 }) {
            Text(text = if (show24) "12 hours" else "24 hours")
        }
    }
}

@Composable
fun DigitalClock(show24: Boolean = true) {
    var timeString by remember { mutableStateOf("") }
    val timeFormat = remember(show24) {
        SimpleDateFormat(if (show24) "HH:mm:ss" else "hh:mm:ss a", Locale.getDefault())
    }

    LaunchedEffect(key1 = show24) {
        while (true) {
            timeString = timeFormat.format(Date())
            delay(1000)
        }
    }

    val (time, noon) = if (!show24) {
        val split = timeString.split(" ")
        Pair(split.getOrElse(0) { "" }, split.getOrElse(1) { "" })
    } else {
        Pair(timeString, "")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = time,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text(
                text = noon,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (noon == "AM") {
                    Color.Red
                } else {
                    Color.Blue
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DigitalClockPreview() {
    DemoTheme {
        SwitchClock()
    }
}