package com.zqy.jetpack_demo.net

data class BaseEntity<T>(
    var data: T,
    val errorCode: Int,
    var errorMsg: String
) : BaseResponse<T> {

    override fun isSuccess(): Boolean {
        return errorCode == 0
    }
}