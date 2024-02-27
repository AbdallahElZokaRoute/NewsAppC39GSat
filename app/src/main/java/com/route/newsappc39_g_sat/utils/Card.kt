package com.route.newsappc39_g_sat.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.route.newsappc39_g_sat.R
import com.route.newsappc39_g_sat.model.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItemCard(model: ArticlesItem) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        GlideImage(
            model = model.urlToImage,
            contentDescription = stringResource(
                R.string.news_photo
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3F)
        )
        Text(text = model.source?.name ?: "", fontSize = 10.sp)
        Text(text = model.title ?: "", fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Text(
            text = model.publishedAt ?: "",
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.End)
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsItemCardPreview() {
    NewsItemCard(
        ArticlesItem()
    )
}

@Composable
fun NewsList(newsList: List<ArticlesItem>) {
    LazyColumn {
        items(newsList.size) { position ->
            NewsItemCard(model = newsList[position])
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsListPreview() {
    NewsList(listOf())
}
