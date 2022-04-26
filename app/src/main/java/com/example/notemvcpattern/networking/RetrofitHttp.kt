package com.example.notemvcpattern.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://626696d663e0f38256843a28.mockapi.io/"
    val SERVER_PRODUCTION = "https://626696d663e0f38256843a28.mockapi.io/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server(): String{
        if (IS_TESTER)
            return SERVER_DEVELOPMENT

        return SERVER_PRODUCTION
    }

    val postService: PostService = retrofit.create(PostService::class.java)
}