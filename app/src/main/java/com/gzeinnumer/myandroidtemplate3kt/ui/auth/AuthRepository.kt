package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import io.reactivex.Flowable

interface AuthRepository {
    fun authWithId(userId: Int): Flowable<BaseResource<ResponseLogin>>
}