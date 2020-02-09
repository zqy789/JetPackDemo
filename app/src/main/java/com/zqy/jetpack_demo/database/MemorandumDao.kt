package com.zqy.jetpack_demo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zqy.jetpack_demo.entity.Memorandum

@Dao
interface MemorandumDao {

    @Query("SELECT * FROM memorandum")
    fun getAll(): LiveData<List<Memorandum>>

    @Delete
    fun deleteWhereId(bean: Memorandum)

    @Insert
    fun insert(bean: Memorandum)

    @Update
    fun updateWhereId(bean: Memorandum)
}