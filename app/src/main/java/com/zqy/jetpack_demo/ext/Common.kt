package com.zqy.jetpack_demo.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import androidx.fragment.app.Fragment
import com.zqy.jetpack_demo.utils.IntentUtils

/**
 *  @author: hyzhan
 *  @date:   2019/5/21
 *  @desc:   TODO
 */
inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(this, T::class.java)
    if (params.isNotEmpty()) IntentUtils.fillIntentArguments(intent, params)
    this.startActivity(intent)
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    val intent = Intent(context, T::class.java)
    if (params.isNotEmpty()) IntentUtils.fillIntentArguments(intent, params)
    activity?.startActivity(intent)
}

inline fun tryCatch(tryBlock: () -> Unit, catchBlock: (Throwable) -> Unit = {}) {
    try {
        tryBlock()
    } catch (t: Throwable) {
        t.printStackTrace()
        catchBlock(t)
    }
}


val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()


val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )


val Int.sp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()