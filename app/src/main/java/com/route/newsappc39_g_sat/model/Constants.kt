package com.route.newsappc39_g_sat.model

import com.route.newsappc39_g_sat.R

object Constants {
    val API_KEY = "cd80ea79c3c0495aa93cf4b0a5be20bc"

    val categories = listOf(
        Category(
            "sports", R.drawable.ball,
            R.string.sports, R.color.red
        ),
        Category(
            "technology", R.drawable.politics,
            R.string.technology, R.color.blue
        ),
        Category(
            "health", R.drawable.health,
            R.string.health, R.color.pink
        ),
        Category(
            "business", R.drawable.bussines,
            R.string.business, R.color.brown_orange
        ),
        Category(
            "general", R.drawable.environment,
            R.string.general, R.color.baby_blue
        ),
        Category(
            "science", R.drawable.science,
            R.string.science, R.color.yellow
        ),
    )
}