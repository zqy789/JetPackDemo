package com.zqy.jetpack_demo.respository

import com.zqy.jetpack_demo.base.BaseModel
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.net.APIManager

class PagingRepository : BaseModel() {

    suspend fun getList(page: Int): MutableList<PagingSampleEntity> {
        return launchIO {
            APIManager.apiService.getList(page).await().data.datas
        }
    }
}