package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.demo.ui.theme.DemoTheme
import com.example.demo.screen.MainScreen
import com.example.demo.screen.ClockScreen
import com.example.demo.screen.TimerScreen
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTheme {
                var currentScreen by remember { mutableStateOf(1) }
                var previousScreen by remember { mutableStateOf(1) }

                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        AnimatedContent(
                            targetState = currentScreen, transitionSpec = {
                                if (previousScreen < targetState) {
                                    slideInHorizontally { it } with slideOutHorizontally { -it }
                                } else {
                                    slideInHorizontally { -it } with slideOutHorizontally { it }
                                }
                            }) { screen ->
                            when (screen) {
                                1 -> MainScreen()
                                2 -> ClockScreen()
                                3 -> TimerScreen()
                            }
                        }
                    }

                    Divider(thickness = 1.dp, color = Color.Gray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .navigationBarsPadding(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val items = listOf(
                            Triple(1, "主页", Icons.Default.Home),
                            Triple(2, "时钟", Icons.Default.DateRange),
                            Triple(3, "计时", Icons.Default.Info)
                        )

                        items.forEach { (route, label, icon) ->
                            val selected = currentScreen == route
                            val color = if (selected) Color.Gray else Color.Black
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clickable(enabled = !selected) {
                                        previousScreen = currentScreen
                                        currentScreen = route
                                    }
                                    .padding(8.dp)
                            ) {
                                Icon(imageVector = icon, contentDescription = label, tint = color)
                                Text(text = label, color = color, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}