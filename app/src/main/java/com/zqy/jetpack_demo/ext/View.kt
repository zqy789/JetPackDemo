package com.zhan.mvp.ext

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.view.ViewConfiguration
import android.os.Build
import android.annotation.TargetApi
import java.lang.reflect.AccessibleObject.setAccessible


/**
 *  @author: hyzhan
 *  @date:   2019/5/17
 *  @desc:   TODO
 */
// 关闭软键盘
fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

// 显示软键盘
fun View.showKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

// 获取text内容
fun TextView.str(): String {
    return this.text.toString()
}


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}






