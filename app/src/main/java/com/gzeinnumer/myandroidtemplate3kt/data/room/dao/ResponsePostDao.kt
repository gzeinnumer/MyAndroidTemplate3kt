package com.gzeinnumer.myandroidtemplate3kt.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gzeinnumer.myandroidtemplate3kt.base.BaseDao
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost


@Dao
abstract class ResponsePostDao : BaseDao<ResponsePost> {

    @Query("SELECT * FROM ResponsePost")
    abstract fun getAll(): List<ResponsePost>

    @Query("DELETE FROM ResponsePost")
    abstract fun truncateData()

    @Query("SELECT COUNT(id) FROM ResponsePost")
    abstract fun getRowCount(): Int
}