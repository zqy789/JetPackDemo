package com.zqy.jetpack_demo.net

data class BasePagingEntity<T>(
    val curPage: Int,
    val datas: T,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)