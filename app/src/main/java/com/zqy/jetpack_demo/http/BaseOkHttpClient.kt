package com.zqy.jetpack_demo.http

import com.zqy.jetpack_demo.http.interceptor.LoggingIntercept
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object BaseOkHttpClient {

    fun init(vararg interceptors: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().run {
            interceptors.forEach { addInterceptor(it) }

            addInterceptor(LoggingIntercept.init())
            readTimeout(HttpConfig.READ_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(HttpConfig.WRITE_TIME_OUT, TimeUnit.SECONDS)
            connectTimeout(HttpConfig.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            build()
        }
    }
}