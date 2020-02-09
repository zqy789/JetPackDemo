package com.zhan.mvp.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 *  @author: hyzhan
 *  @date:   2019/5/16
 *  @desc:   Toast 扩展函数
 */

@SuppressLint("StaticFieldLeak")
object Toasts {

    private var mToast: Toast? = null
    private var context: Context? = null
    /**
     *  如果 mToast 没有初始化, 就创建一个 Toast, 并赋值
     *  否则就直接显示
     */
    private fun showToast(message: String, duration: Int) {
        context?.let {
            mToast?.let {
                it.duration = duration
                it.setText(message)
                it.show()
            } ?: Toast.makeText(context, message, duration).apply {
                mToast = this
                show()
            }
        } ?: Throwable("Toast context is null")
    }

    /**
     *  防止重复 showToast 显示
     *  如果 mToast不为空 就显示, 否则就创建新的 mToast
     */
    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        showToast(message, duration)
    }

    fun toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        toast(message.toString(), duration)
    }

    fun init(context: Context) {
        this.context = context
    }
}
