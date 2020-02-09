package com.zqy.jetpack_demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zqy.jetpack_demo.entity.HomeListEntity

class ThisViewModel : ViewModel() {

    val dataBean: MutableLiveData<Int> = MutableLiveData()
    var count = 0

    fun addData() {
        println("ThisViewModel:$count")
        count++
        dataBean.postValue(count)
    }
}