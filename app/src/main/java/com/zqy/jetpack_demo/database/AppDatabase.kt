package com.zqy.jetpack_demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zqy.jetpack_demo.entity.Memorandum

@Database(
    entities =
    [
        Memorandum::class
    ], version = 3
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memorandumDao(): MemorandumDao


    /**
     * 设计成单例模式，避免创建多个数据库对象消耗资源
     */
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "memorandum"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        println("创建数据库")
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        println("打开数据库")
                    }
                }).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}

