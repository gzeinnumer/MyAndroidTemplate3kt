package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

        val posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()

        posts.value = BaseResource.loading()
        val data: List<ResponsePost> = repository.getList()

        if (data.isNotEmpty()) {
            myLogD(TAG, func + "Jumlah data " + data.size)
            posts.setValue(BaseResource.success("Success dapat data local", data))
        } else {
            myLogD(TAG, func + "Jumlah data " + data.size)
            posts.setValue(BaseResource.error("Tidak ada data dalam local"))
        }
        return posts
    }
}