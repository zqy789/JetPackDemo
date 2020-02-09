package com.zqy.jetpack_demo.http.exception

object ErrorCode {
    /**
     * 未知错误
     */
    val UNKNOWN = 1000
    /**
     * 解析错误
     */
    val PARSE_ERROR = 1001
    /**
     * 网络错误
     */
    val NETWORD_ERROR = 1002
    /**
     * 协议出错
     */
    val HTTP_ERROR = 1003
    /**
     * 证书出错
     */
    val SSL_ERROR = 1005
}