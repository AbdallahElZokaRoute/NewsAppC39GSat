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
import com.route.newsappc39_g_sat.api.APIManager
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.model.SourcesItem
import com.route.newsappc39_g_sat.model.SourcesResponse
import com.route.newsappc39_g_sat.ui.theme.green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun NewsSourcesTabRow(onTabSelected: (String) -> Unit) {
    val context = LocalContext.current
    val selectedTabIndex = remember {
        mutableIntStateOf(0)
    }
    val sourcesList = remember {
        mutableStateListOf<SourcesItem>()
    }
    LaunchedEffect(Unit) {
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, "sports")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.body()?.sources?.isNotEmpty() == true)
                        sourcesList.addAll(response.body()?.sources!!)

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }


            })// Background Thread ->
//            .execute() // Main Thread ->
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex.intValue,
        edgePadding = 16.dp,
        indicator = {},
        divider = {}) {

        sourcesList.forEachIndexed { index, item ->
            LaunchedEffect(Unit) {
                if (selectedTabIndex.intValue == 0) {
                    onTabSelected(item.id ?: "")
                }
            }
            Tab(
                selected = index == selectedTabIndex.intValue,
                onClick = {
                    selectedTabIndex.intValue = index
                    onTabSelected(item.id ?: "")
                },
                selectedContentColor = Color.White,
                unselectedContentColor = green
            ) {
                Text(
                    text = item.name ?: "",
                    fontSize = 14.sp,
                    modifier = if (selectedTabIndex.intValue == index)
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