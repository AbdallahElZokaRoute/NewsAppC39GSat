package com.route.newsappc39_g_sat.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsappc39_g_sat.api.APIManager
import com.route.newsappc39_g_sat.model.ArticlesItem
import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.Category
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.utils.NewsList
import com.route.newsappc39_g_sat.utils.NewsSourcesTabRow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsFragment(modifier: Modifier = Modifier, categoryId: String) {
    val articlesList = remember {
        mutableStateListOf<ArticlesItem>()
    }
    Column(modifier = modifier.fillMaxSize()) {
        NewsSourcesTabRow(categoryId) { tabSelectedId ->
            APIManager
                .getNewsServices()
                .getNewsArticles(Constants.API_KEY, tabSelectedId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        articlesList.clear()
                        if (response.body()?.articles?.isNotEmpty() == true)
                            articlesList.addAll(response.body()?.articles!!)
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
        NewsList(articlesList.toList())
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsFragmentPreview() {
    NewsFragment(categoryId = "sports")
}