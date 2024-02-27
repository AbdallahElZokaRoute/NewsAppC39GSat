package com.route.newsappc39_g_sat.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc39_g_sat.R
import com.route.newsappc39_g_sat.ui.theme.green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(onNavigationClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.news_app),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = green,
            titleContentColor = Color.White
        ),
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEnd = 24.dp,
                bottomStart = 24.dp
            )
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(
                    R.string.icon_navigation_menu
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        onNavigationClick()
                    }
            )
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(
                    R.string.icon_search_action
                ), modifier = Modifier.padding(end = 10.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewsTopAppBarPreview() {
    NewsTopAppBar {

    }
}