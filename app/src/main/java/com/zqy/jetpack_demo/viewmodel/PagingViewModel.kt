package com.zqy.jetpack_demo.viewmodel

import androidx.lifecycle.ViewModel
import com.zqy.jetpack_demo.entity.PagingSampleEntity
import com.zqy.jetpack_demo.net.BaseEntity
import com.zqy.jetpack_demo.net.BasePagingEntity
import com.zqy.jetpack_demo.respository.PagingRepository

class PagingViewModel:ViewModel() {

    suspend fun getList(page:Int): MutableList<PagingSampleEntity> {
        return PagingRepository().getList(page)
    }
}