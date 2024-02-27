package com.route.newsappc39_g_sat.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsServices(): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}