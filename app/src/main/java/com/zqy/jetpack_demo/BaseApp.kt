package com.zqy.jetpack_demo

import android.app.Application
import com.zhan.mvp.ext.Toasts
import com.zqy.jetpack_demo.http.HttpConfig

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化吐司
        Toasts.init(this)
        //初始化 retrofit
        HttpConfig.getRetrofit()
    }
}