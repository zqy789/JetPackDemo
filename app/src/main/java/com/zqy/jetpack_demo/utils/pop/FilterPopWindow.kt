package com.zqy.jetpack_demo.utils.pop

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.zqy.jetpack_demo.R


class FilterPopWindow(
    private var context: Activity,
    monitor: PopClickImpl
) : PopupWindow() {

    private var inflate: View? = null

    init {
        //设置宽高
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        //背景
        isOutsideTouchable = true
        isFocusable = true
        //设置填充布局
        inflate =
            LayoutInflater.from(context).inflate(R.layout.layout_filter_popwindow, null).apply {
                findViewById<LinearLayout>(R.id.pop_update).setOnClickListener { monitor.itemClick(0) }
                findViewById<LinearLayout>(R.id.pop_create).setOnClickListener { monitor.itemClick(1) }
                findViewById<LinearLayout>(R.id.pop_title).setOnClickListener { monitor.itemClick(2) }
                findViewById<LinearLayout>(R.id.pop_cancel).setOnClickListener {
                    monitor.itemClick(3)
                    dismiss()
                }
            }
        contentView = inflate
    }


    fun show(view: View) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0)
    }

    /**
     * 根据设置的view 的位置进行显示
     */
    override fun showAsDropDown(anchor: View?) {
        setBackAlpha()
    }

    /**
     * 设置坐标位置显示
     */
    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        if (isShowing) {
            dismiss()
        } else {
            super.showAtLocation(parent, gravity, x, y)
            setWindowBackgroundAlpha(0.7F)
        }
    }

    private fun setBackAlpha() {
        if (isShowing) {
            dismiss()
        } else {
            super.showAsDropDown(contentView)
            setWindowBackgroundAlpha(0.7F)
        }
    }


    override fun dismiss() {
        super.dismiss()
        //回复activity背景正常
        setWindowBackgroundAlpha(1.0F)
    }

    /**
     * 控制窗口背景的不透明度
     */
    public fun setWindowBackgroundAlpha(alpha: Float) {
        val window = context.window
        val layoutParams = window.attributes
        layoutParams.alpha = alpha
        window.attributes = layoutParams
    }

}