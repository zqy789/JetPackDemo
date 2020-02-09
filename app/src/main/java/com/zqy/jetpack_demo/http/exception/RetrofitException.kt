package com.zqy.jetpack_demo.http.exception

import com.google.gson.JsonParseException

import org.json.JSONException

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

import javax.net.ssl.SSLHandshakeException

import retrofit2.HttpException

class RetrofitException {

    class ResponseThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
        override var message: String? = null
    }

    companion object {

        private val UNAUTHORIZED = 401
        private val FORBIDDEN = 403
        private val NOT_FOUND = 404
        private val REQUEST_TIMEOUT = 408
        private val INTERNAL_SERVER_ErrorCode = 500
        private val BAD_GATEWAY = 502
        private val SERVICE_UNAVAILABLE = 503
        private val GATEWAY_TIMEOUT = 504

        fun retrofitException(e: Throwable): ResponseThrowable {
            val ex: ResponseThrowable

            if (e is HttpException) {
                ex = ResponseThrowable(
                    e,
                    ErrorCode.HTTP_ERROR
                )
                when (e.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ErrorCode, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message =
                        "网络错误"
                    else -> ex.message = "网络错误"
                }
                return ex
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ex = ResponseThrowable(
                    e,
                    ErrorCode.PARSE_ERROR
                )
                ex.message = "解析错误"
                return ex
            } else if (e is ConnectException
                || e is SocketTimeoutException
                || e is UnknownHostException
            ) {
                ex = ResponseThrowable(
                    e,
                    ErrorCode.NETWORD_ERROR
                )
                ex.message = "连接失败"
                return ex
            } else if (e is SSLHandshakeException) {
                ex = ResponseThrowable(
                    e,
                    ErrorCode.SSL_ERROR
                )
                ex.message = "证书验证失败"
                return ex
            } else {
                ex = ResponseThrowable(
                    e,
                    ErrorCode.UNKNOWN
                )
                ex.message = "未知错误"
                return ex
            }//		else if (e instanceof ServerException) {
            //			// 服务器下发的错误
            //			ServerException resultException = (ServerException) e;
            //			ex = new ResponseThrowable(resultException, resultException.code);
            //			ex.message = resultException.getMessage();
            //			return ex;
            //		}
        }
    }
}
