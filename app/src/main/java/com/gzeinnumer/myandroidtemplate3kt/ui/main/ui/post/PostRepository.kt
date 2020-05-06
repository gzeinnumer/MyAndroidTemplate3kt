package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.util.Log
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

interface PostRepository {
    fun getPotsFromUserRx1(userId: Int) : Flowable<BaseResource<List<ResponsePost>>>
}