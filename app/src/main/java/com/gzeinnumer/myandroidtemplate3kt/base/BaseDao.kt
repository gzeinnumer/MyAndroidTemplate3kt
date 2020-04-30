package com.gzeinnumer.myandroidtemplate3kt.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objs: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnoreAll(objs: List<T>): List<Long>

    @Update
    fun update(obj: T)

    @Update
    fun updateAll(objs: List<T>): Int

    @Delete
    fun delete(obj: T)

    @Delete
    fun deleteAll(objs: List<T>): Int

}
