package com.zqy.jetpack_demo.ext

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zqy.jetpack_demo.R
import com.zqy.jetpack_demo.adapter.MemorandumAdapter

/**
 * 设置空布局
 */
fun MemorandumAdapter.setMyEmptyView(context: Context): View {
    val inflate = View.inflate(context, R.layout.base_enpty_layout, null)
    emptyView = inflate
    return inflate
}
