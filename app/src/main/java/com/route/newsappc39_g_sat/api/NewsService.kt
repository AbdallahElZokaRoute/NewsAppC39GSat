package com.route.newsappc39_g_sat.api

import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
    ): Call<ArticlesResponse>
}
