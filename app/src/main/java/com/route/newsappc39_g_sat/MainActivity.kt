package com.route.newsappc39_g_sat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc39_g_sat.ui.theme.NewsAppC39GSatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSatTheme {
                CounterScreen()
            }
        }
    }
}

data class SettingsItem(val id: String)

@Composable
fun CounterScreen() {
    //   counter =
    val counter = remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You pressed ${counter.intValue} times", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            counter.intValue++
        }) {
            Text(text = "Increment!", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { counter.intValue-- }) {
            Text(text = "Decrement", fontSize = 30.sp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CounterPreview() {
    CounterScreen()
}