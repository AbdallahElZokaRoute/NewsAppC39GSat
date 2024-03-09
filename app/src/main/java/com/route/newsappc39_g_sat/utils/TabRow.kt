package com.route.newsappc39_g_sat.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsappc39_g_sat.api.APIManager
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.model.SourcesItem
import com.route.newsappc39_g_sat.model.SourcesResponse
import com.route.newsappc39_g_sat.news.NewsViewModel
import com.route.newsappc39_g_sat.ui.theme.green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun NewsSourcesTabRow(
    categoryId: String,
    viewModel: NewsViewModel = viewModel(),
    onTabSelected: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getNewsSources(categoryId)
        // Background Thread ->
//            .execute() // Main Thread ->
    }

    ScrollableTabRow(
        selectedTabIndex = viewModel.selectedTabIndex.intValue,
        edgePadding = 16.dp,
        indicator = {},
        divider = {}) {

        viewModel.sourcesList.forEachIndexed { index, item ->
            LaunchedEffect(Unit) {
                if (viewModel.sourcesList.isNotEmpty()) {
                    onTabSelected(viewModel.sourcesList.get(0).id ?: "")
                }
            }
            Tab(
                selected = index == viewModel.selectedTabIndex.intValue,
                onClick = {
                    if (viewModel.selectedTabIndex.intValue != index) {
                        viewModel.selectedTabIndex.intValue = index
                        onTabSelected(item.id ?: "")
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green
            ) {
                Text(
                    text = item.name ?: "",
                    fontSize = 14.sp,
                    modifier = if (viewModel.selectedTabIndex.intValue == index)
                        Modifier
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                            .background(green, CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    else
                        Modifier
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                            .border(2.dp, green, CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}