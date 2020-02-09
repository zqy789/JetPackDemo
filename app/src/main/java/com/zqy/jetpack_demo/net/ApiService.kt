package com.zqy.jetpack_demo.net

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.ResponseBody


/**
 * 接口声明，接收类
 */
interface ApiService {

    /**
     * 查询分页列表
     */
    @GET("${API.list}{page}/json")
    fun getList(@Path("page") page: Int):
            Deferred<BaseEntity<BasePagingEntity<MutableList<PagingSampleEntity>>>>


}