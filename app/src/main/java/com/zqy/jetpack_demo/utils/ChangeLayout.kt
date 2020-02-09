package com.zqy.jetpack_demo.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class ChangeLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     *
     */
    fun layoutCheck(flag: Boolean) {
        if (flag) {

        }
    }
}