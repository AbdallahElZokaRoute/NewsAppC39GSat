package com.route.newsappc39_g_sat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc39_g_sat.news.NewsActivity
import com.route.newsappc39_g_sat.ui.theme.NewsAppC39GSatTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSatTheme {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@SplashActivity, NewsActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2500)
                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
// Avoid using nested Rows and column -> Constraint layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds //scaleType="fitXY"
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo_image),
            modifier = Modifier.fillMaxHeight(0.3F),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = stringResource(
                R.string.app_development_signature
            ),
            modifier = Modifier.fillMaxWidth(0.4F),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    SplashContent()
}
