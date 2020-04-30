package com.gzeinnumer.myandroidtemplate3kt.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gzeinnumer.myandroidtemplate3kt.base.BaseDao
import com.gzeinnumer.myandroidtemplate3kt.data.room.table.DummyTable

@Dao
abstract class DummyDao: BaseDao<DummyTable> {
    @Query("SELECT * FROM dummy")
    abstract fun getAll(): List<DummyTable>

    @Query("DELETE FROM dummy")
    abstract fun truncateData()

    @Query("SELECT COUNT(id) FROM dummy")
    abstract fun getRowCount(): Int
}