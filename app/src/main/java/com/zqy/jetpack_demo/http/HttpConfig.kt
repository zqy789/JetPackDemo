package com.zqy.jetpack_demo.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object HttpConfig {

    //const val readTimeout =
    const val BASE_URL = "https://www.wanandroid.com"
    // 读超时
    const val READ_TIME_OUT = 20L
    // 写超时
    const val WRITE_TIME_OUT = 30L
    // 连接超时
    const val CONNECT_TIME_OUT = 5L

    fun getOkHttp(): OkHttpClient = BaseOkHttpClient.init()

    fun getRetrofit():Retrofit = BaseRetrofit.init()
}