package com.gzeinnumer.myandroidtemplate3kt.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.gzeinnumer.myandroidtemplate3kt.data.room.dao.DummyDao
import com.gzeinnumer.myandroidtemplate3kt.data.room.table.DummyTable
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD

@Database(
    entities = [
        DummyTable::class
    ]
    , version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun storeDummyDao(): DummyDao

    companion object {

        val TAG = "ApDatabase"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            val func = "getInstance+"
            myLogD(TAG,func)

            return Room
                .databaseBuilder(context.applicationContext, AppDatabase::class.java, "gzeinnumer")
                .allowMainThreadQueries()
                .build()
        }
    }
}