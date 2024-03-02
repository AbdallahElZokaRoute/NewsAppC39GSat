package com.route.newsappc39_g_sat.api

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {
    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.e("API", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsServices(): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}