package com.example.notemvcpattern.model

import com.google.gson.annotations.SerializedName

data class PosterResp(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("theme")
    val theme: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("time")
    val time: String? = null,
)