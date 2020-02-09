package com.zqy.jetpack_demo.http

object RetrofitFactory {


    private val retrofit by lazy {
        HttpConfig.getRetrofit()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}