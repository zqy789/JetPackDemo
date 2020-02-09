package com.zqy.jetpack_demo.paging

import androidx.paging.PageKeyedDataSource
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.ext.launchUI
import com.zqy.jetpack_demo.respository.PagingRepository

class PagingDataSource : PageKeyedDataSource<Int, PagingSampleEntity>() {
    /**
     * 加载第一页
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PagingSampleEntity>
    ) {
        launchUI({
            val list = PagingRepository().getList(0)
            callback.onResult(list, 0, 1)
        })
    }

    /**
     * 加载其他
     */
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PagingSampleEntity>
    ) {
        launchUI({
            val list = PagingRepository().getList(params.key)
            callback.onResult(list, params.key.plus(1))
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PagingSampleEntity>
    ) {
    }


}