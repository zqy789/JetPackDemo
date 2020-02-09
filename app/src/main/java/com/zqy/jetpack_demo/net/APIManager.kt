package com.zqy.jetpack_demo.net

import com.zqy.jetpack_demo.http.RetrofitFactory

object APIManager {
    val apiService by lazy {
        RetrofitFactory.create(ApiService::class.java)
    }
}