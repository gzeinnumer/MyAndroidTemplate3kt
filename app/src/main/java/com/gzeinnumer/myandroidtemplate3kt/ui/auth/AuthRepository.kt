package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import io.reactivex.Flowable
import io.reactivex.Observable

interface AuthRepository {
    fun authWithIdCall(userId: Int): LiveData<BaseResource<ResponseLogin>>
    fun authWithIdRx1(userId: Int): Flowable<BaseResource<ResponseLogin>>
    fun authWithIdRx2(userId: Int): MutableLiveData<BaseResource<ResponseLogin>>
    fun authWithIdCoroutines(userId: Int): MutableLiveData<BaseResource<ResponseLogin>>
}