package com.zqy.jetpack_demo.respository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import com.zqy.jetpack_demo.BaseModel
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.net.APIManager
import com.zqy.jetpack_demo.net.BaseEntity
import com.zqy.jetpack_demo.net.BasePagingEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PagingRepository : BaseModel() {

    suspend fun getList(page: Int): MutableList<PagingSampleEntity> {
        return launchIO {
            APIManager.apiService.getList(page).await().data.datas
        }
    }
}