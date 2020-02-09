package com.zqy.jetpack_demo.ext

import com.zhan.mvp.ext.Toasts
import com.zqy.jetpack_demo.http.exception.RetrofitException
import kotlinx.coroutines.*

/**
 * 用于处理在使用协程是出现时的异常，这大部分用作与网络
 * @author zqy
 */
val presenterScope: CoroutineScope by lazy {
    CoroutineScope(Dispatchers.Main + Job())
}

fun launchUI(block: suspend CoroutineScope.() -> Unit, error: ((Throwable) -> Unit)? = null) {
    presenterScope.launch {
        tryCatch({
            block()
        }, {
            error?.invoke(it) ?: showException(it)
        })
    }
}

/**
 * 异常捕获
 */
private fun showException(throwable: Throwable) {
    val errorMessage = RetrofitException.retrofitException(
        throwable
    ).message
    errorMessage?.let {
        Toasts.toast(it)
    }

}

fun <R> quickLaunch(block: Execute<R>.() -> Unit) {
    Execute<R>().apply(block)
}


//    fun <R> execute<R>(success: ((R?) -> Unit)?, error: ((String) -> Unit)? = null) {
//        if (this.isSuccess()) {
//            success?.invoke(this.getKData())
//            return
//        }
//
//        error?.invoke(this.getKMessage()) ?: showError(this.getKMessage())
//    }


fun detachView() {
    // 取消掉 presenterScope创建的所有协程和其子协程。
    presenterScope.cancel()
}


class Execute<R> {

    private var successBlock: ((R?) -> Unit)? = null
    private var failBlock: ((String?) -> Unit)? = null
    private var exceptionBlock: ((Throwable?) -> Unit)? = null

//        fun request(block: suspend CoroutineScope.() -> KResponse<R>?) {
//            launchUI({ block()?.execute(successBlock, failBlock) }, exceptionBlock)
//        }

    fun onSuccess(block: (R?) -> Unit) {
        this.successBlock = block
    }

    fun onFail(block: (String?) -> Unit) {
        this.failBlock = block
    }

    fun onException(block: (Throwable?) -> Unit) {
        this.exceptionBlock = block
    }
}