package com.example.demo

import android.app.ActivityOptions
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun NavigatorBar() {
    val context = LocalContext.current

    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (context !is MainActivity) {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    val options = ActivityOptions.makeCustomAnimation(
                        context, R.anim.slide_in_left, R.anim.slide_to_right
                    )
                    context.startActivity(intent, options.toBundle())
                }
            }) {
                Text("Main")
            }
            Button(onClick = {
                if (context !is ClockActivity) {
                    val intent = Intent(context, ClockActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    val options = ActivityOptions.makeCustomAnimation(
                        context, R.anim.slide_in_right, R.anim.slide_to_left
                    )
                    context.startActivity(intent, options.toBundle())
                }
            }) {
                Text("Clock")
            }
        }
    }
}