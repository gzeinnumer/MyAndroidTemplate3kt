package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import androidx.lifecycle.LiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost

interface PostLocalRepository {
    fun getList(): LiveData<BaseResource<List<ResponsePost>>>
    fun getListFLowable(): LiveData<BaseResource<List<ResponsePost>>>
    fun getListMaybe(): LiveData<BaseResource<List<ResponsePost>>>
    fun getListSingle(): LiveData<BaseResource<List<ResponsePost>>>
}