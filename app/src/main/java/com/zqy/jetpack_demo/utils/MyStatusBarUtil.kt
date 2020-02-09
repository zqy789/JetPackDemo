package com.zqy.jetpack_demo.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View

object MyStatusBarUtil {


    fun setNoStatus(context: Context) {
        if (Build.VERSION.SDK_INT >= 21) {
            val activity = context as Activity
            activity.apply {
                window.decorView.apply {
                    systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
                window.statusBarColor = Color.TRANSPARENT

            }
//            supportActionBar
        }
    }
}