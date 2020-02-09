package com.zqy.jetpack_demo.paging

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zqy.jetpack_demo.entity.PagingSampleEntity

/**
 * Paging配置
 * @author zqy
 */
object PagingHelper {

    /**
     * @param factory
     */
    fun <T> init(@NonNull factory: DataSource.Factory<Int,T>): LiveData<PagedList<T>> {
        return LivePagedListBuilder(
            factory,
            PagedList.Config.Builder()
                // 分页加载的数量
                .setPageSize(20)
                // 是否启动PlaceHolders
                .setEnablePlaceholders(false)
                .build()
        ).build()
    }
//    /**
//     * @param factory
//     */
//    fun  init(): LiveData<PagedList<PagingSampleEntity>> {
//        return LivePagedListBuilder(
//            PagingFactory(),
//            PagedList.Config.Builder()
//                // 分页加载的数量
//                .setPageSize(20)
//                // 是否启动PlaceHolders
//                .setEnablePlaceholders(false)
//                .build()
//        ).build()
//    }
}