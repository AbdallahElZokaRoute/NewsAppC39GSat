package com.route.newsappc39_g_sat.news

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.route.newsappc39_g_sat.api.APIManager
import com.route.newsappc39_g_sat.model.ArticlesItem
import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.model.SourcesItem
import com.route.newsappc39_g_sat.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    // Logic & states
    val articlesList = mutableStateListOf<ArticlesItem>()
    val sourcesList = mutableStateListOf<SourcesItem>()

    val selectedTabIndex = mutableIntStateOf(0)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")
    fun getNewsSources(categoryId: String) {
        isLoading.value = true
        APIManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.e("TAAG", "onResponse: ")
                    isLoading.value = false
                    if (response.body()?.sources?.isNotEmpty() == true)
                        sourcesList.addAll(response.body()?.sources!!)

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("TAAG", "onFailure: ")
                    isLoading.value = false
                    errorMessage.value = t.message ?: ""
                }


            })
    }

    fun getNewsBySource(sourceId: String) {
        isLoading.value = true
        APIManager
            .getNewsServices()
            .getNewsArticles(Constants.API_KEY, sources = sourceId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    isLoading.value = false
                    articlesList.clear()
                    if (response.body()?.articles?.isNotEmpty() == true)
                        articlesList.addAll(response.body()?.articles!!)
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    isLoading.value = false
                    errorMessage.value = t.message ?: ""
                }
            })
    }

}
