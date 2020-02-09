package com.zqy.jetpack_demo.utils.pop

import android.view.View
import android.widget.PopupWindow
import com.zqy.jetpack_demo.BaseApp

class BasePopWindow :PopupWindow(){

//
//    /**
//     * 根据设置的view 的位置进行显示
//     */
//    override fun showAsDropDown(anchor: View?) {
//        setBackAlpha()
//    }
//
//    /**
//     * 设置坐标位置显示
//     */
//    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
//        if (isShowing) {
//            dismiss()
//        } else {
//            super.showAtLocation(parent, gravity, x, y)
//            setWindowBackgroundAlpha(0.7F)
//        }
//    }
//
//    private fun setBackAlpha() {
//        if (isShowing) {
//            dismiss()
//        } else {
//            super.showAsDropDown(contentView)
//            setWindowBackgroundAlpha(0.7F)
//        }
//    }
//
//
//    override fun dismiss() {
//        super.dismiss()
//        //回复activity背景正常
//        setWindowBackgroundAlpha(1.0F)
//    }
//
//    /**
//     * 控制窗口背景的不透明度
//     */
//    public fun setWindowBackgroundAlpha(alpha: Float) {
//        val context = BaseApp().applicationContext
//        val window = context.window
//        val layoutParams = window.attributes
//        layoutParams.alpha = alpha
//        window.attributes = layoutParams
//    }

}