package com.zqy.jetpack_demo.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Memorandum(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    var title: String,
    var updateTime: Long,
    var content: String
) : Parcelable