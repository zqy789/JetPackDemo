package com.zqy.jetpack_demo.base

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhan.mvp.base.BaseActivity
import com.zqy.jetpack_demo.R
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 *  @author: hyzhan
 *  @date:   2019/6/4
 *  @desc:   TODO
 */
abstract class BaseListActivity<T> : BaseActivity() {

    // 当前页
    var page = 0

    val adapter by lazy {
        bindAdapter()
    }

    val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_base_list

    override fun initView() {
        super.initView()

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 初始化 article
        initRecyclerView()
    }

    override fun initData() {
        super.initData()

        onRefreshData()
    }

    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        mSrlRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.color_8BC34A,
            R.color.color_red
        )
        mSrlRefresh.setOnRefreshListener {
            page = 0
            onRefreshData()
        }
    }

    private fun initRecyclerView() {

        mRvContent.layoutManager = linearLayoutManager
        mRvContent.adapter = adapter

        // 上拉加载更多
        adapter.setOnLoadMoreListener({
            ++page
            onLoadMoreData()
        }, mRvContent)
    }

    abstract fun bindAdapter(): BaseQuickAdapter<T, *>
    /**
     *  下拉刷新
     */
    open fun onRefreshData() {}

    /**
     *  上拉加载更多
     */
    open fun onLoadMoreData() {}

    fun addData(newData: List<T>) {
        // 如果为空的话，就直接 显示加载完毕
        if (newData.isEmpty()) {
            adapter.loadMoreEnd()
            return
        }

        // 如果是 下拉刷新 直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            adapter.setNewData(newData)
            adapter.loadMoreComplete()
            return
        }

        // 否则 添加新数据
//        adapter.addData(newData)
        adapter.loadMoreComplete()
    }
}
