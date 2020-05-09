package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

@MainScope
class PostLocalRepositoryImpl @Inject constructor(val db: AppDatabase): PostLocalRepository{

    private val TAG = "PostLocalRepositoryImpl"

    init {
        myLogD(TAG, "PostLocalRepositoryImpl: ready")
    }

    override fun getList(): List<ResponsePost> {
        val func = "getList+"
        myLogD(TAG, func)

        return db.storeResponsePostDao().getAll()
    }
}