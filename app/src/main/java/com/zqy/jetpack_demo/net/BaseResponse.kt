package com.zqy.jetpack_demo.net

interface BaseResponse<T> {
    fun isSuccess(): Boolean
}