package com.example.demo.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import kotlinx.coroutines.delay

@Composable
fun TimerScreen() {
    var isRunning by remember { mutableStateOf(false) }
    var timeMillis by remember { mutableStateOf(0L) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            timeMillis += 10
            delay(10)
        }
    }

    val minutes = (timeMillis / 1000) / 60
    val seconds = (timeMillis / 1000) % 60
    val millis = (timeMillis % 1000) / 10

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = String.format("%02d:%02d:%02d", minutes, seconds, millis),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button(
                onClick = { isRunning = !isRunning },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) Color(0xFFF44336) else Color(0xFF03BCF4),
                    contentColor = Color.White
                ),
            ) {
                Text(if (isRunning) "暂停" else "开始")
            }
            Spacer(modifier = Modifier.height(2.dp))
            Button(
                onClick = {
                    timeMillis = 0L
                    isRunning = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
            ) {
                Text("结束")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerPreview() {
    DemoTheme {
        TimerScreen()
    }
}
