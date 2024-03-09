package com.route.newsappc39_g_sat.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.route.newsappc39_g_sat.news.NewsViewModel
import com.route.newsappc39_g_sat.utils.NewsList
import com.route.newsappc39_g_sat.utils.NewsSourcesTabRow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsappc39_g_sat.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFragment(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = viewModel(),
    categoryId: String
) {
    if (viewModel.isLoading.value) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = green)
        }
    }
    if (viewModel.errorMessage.value.isNotEmpty()) {
        AlertDialog(onDismissRequest = { viewModel.errorMessage.value = "" }) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = viewModel.errorMessage.value)
                TextButton(onClick = { viewModel.errorMessage.value = "" }) {
                    Text(text = "OK")
                }
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        NewsSourcesTabRow(categoryId) { tabSelectedId ->
            viewModel.getNewsBySource(tabSelectedId)
        }
        NewsList(viewModel.articlesList.toList())
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsFragmentPreview() {
    NewsFragment(categoryId = "sports")
}