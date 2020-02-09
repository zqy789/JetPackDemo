package com.zqy.jetpack_demo.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseRetrofit {

    fun init(): Retrofit {
        return Retrofit.Builder().run {
            baseUrl(HttpConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            //@CoroutineCallAdapterFactory 协程
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            client(HttpConfig.getOkHttp())
            build()
        }
    }
}