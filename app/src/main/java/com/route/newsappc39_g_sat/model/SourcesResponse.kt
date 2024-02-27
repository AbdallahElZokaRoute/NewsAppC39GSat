package com.route.newsappc39_g_sat.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
)