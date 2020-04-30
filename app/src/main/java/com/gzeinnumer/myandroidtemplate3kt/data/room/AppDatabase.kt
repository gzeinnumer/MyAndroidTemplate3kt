package com.gzeinnumer.myandroidtemplate3kt.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.gzeinnumer.myandroidtemplate3kt.data.room.dao.DummyDao
import com.gzeinnumer.myandroidtemplate3kt.data.room.table.DummyTable

@Database(
    entities = [
        DummyTable::class
    ]
    , version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storeDummyDao(): DummyDao

    companion object {

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context.applicationContext, AppDatabase::class.java, "gzeinnumer")
                .allowMainThreadQueries()
                .build()
        }
    }
}