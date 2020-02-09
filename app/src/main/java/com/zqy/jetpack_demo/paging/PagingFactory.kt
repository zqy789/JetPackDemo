package com.zqy.jetpack_demo.paging

import androidx.paging.DataSource
import com.zqy.jetpack_demo.entity.PagingSampleEntity

class PagingFactory:DataSource.Factory<Int, PagingSampleEntity>() {
    override fun create(): DataSource<Int, PagingSampleEntity> {
        return  PagingDataSource()
    }
}