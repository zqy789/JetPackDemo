package com.zqy.jetpack_demo.http.interceptor

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object LoggingIntercept {

    fun init(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}