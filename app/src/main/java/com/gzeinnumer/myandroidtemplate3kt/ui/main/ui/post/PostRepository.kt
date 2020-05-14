package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

interface PostRepository {
    fun getPostFromUserCall(userId: Int?, isLoadNew: Boolean): LiveData<BaseResource<List<ResponsePost>>>
    fun getPotsFromUserRx1(userId: Int?): Flowable<BaseResource<List<ResponsePost>>>
    fun getPotsFromUserRx2(userId: Int?, isLoadNew: Boolean): LiveData<BaseResource<List<ResponsePost>>>
    fun getPotsFromUserCoroutines(userId: Int?, isLoadNew: Boolean): LiveData<BaseResource<List<ResponsePost>>>
}