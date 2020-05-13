package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

class PostLocalVM @Inject constructor(val repository: PostLocalRepositoryImpl) : ViewModel() {

    private val TAG = "PostLocalVM"

    fun getList(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getList+"
        myLogD(TAG, func)

        return repository.getList()
    }

    fun getListFLowable(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListFLowable+"
        myLogD(TAG, func)

        return repository.getListFLowable()
    }

    fun getListMayBe(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListMayBe+"
        myLogD(TAG, func)

        return repository.getListMaybe()
    }

    fun getListSingle(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListSingle+"
        myLogD(TAG, func)

        return repository.getListSingle()
    }
}