package com.route.newsappc39_g_sat.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc39_g_sat.R
import com.route.newsappc39_g_sat.ui.theme.green

@Composable
fun NewsDrawerSheet(onSettingsClickListener: () -> Unit, onCategoriesClickListener: () -> Unit) {
    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7F), drawerContainerColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.18F)
                .background(green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.news_app),
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        DrawerItem(
            iconResId = R.drawable.ic_categories,
            text = stringResource(id = R.string.categories)
        ) {
            onCategoriesClickListener()
        }
        DrawerItem(
            iconResId = R.drawable.ic_settings,
            text = stringResource(id = R.string.settings)
        ) {
            onSettingsClickListener()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsDrawerSheetPreview() {
    NewsDrawerSheet(onSettingsClickListener = {}, onCategoriesClickListener = {})
}

@Composable
fun DrawerItem(iconResId: Int, text: String, onClickListener: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClickListener()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(R.string.navigation_drawer_item)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = text, fontSize = 24.sp)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DrawerItemPreview() {
    DrawerItem(iconResId = R.drawable.ic_categories, text = stringResource(R.string.categories)) {

    }
}