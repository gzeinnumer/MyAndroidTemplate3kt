package com.gzeinnumer.myandroidtemplate3kt.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gzeinnumer.myandroidtemplate3kt.base.BaseDao
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single


@Dao
abstract class ResponsePostDao : BaseDao<ResponsePost> {

    @Query("SELECT * FROM ResponsePost")
    abstract fun getAll(): List<ResponsePost>

    @Query("DELETE FROM ResponsePost")
    abstract fun truncateData()

    @Query("SELECT COUNT(id) FROM ResponsePost")
    abstract fun getRowCount(): Int

    @Query("SELECT * FROM ResponsePost")
    abstract fun getAllFlowable(): Flowable<List<ResponsePost>>

    @Query("SELECT * FROM ResponsePost")
    abstract fun getAllSingle(): Single<List<ResponsePost>>

    @Query("SELECT * FROM ResponsePost")
    abstract fun getAllMaybe(): Maybe<List<ResponsePost>>
}