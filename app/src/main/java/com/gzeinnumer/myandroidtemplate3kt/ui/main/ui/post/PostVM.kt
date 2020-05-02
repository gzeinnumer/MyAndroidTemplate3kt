package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi.MainApi
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostVM @Inject constructor(val sessionManager: SessionManager, val mainApi: MainApi): ViewModel() {

    companion object {
        private const val TAG = "PostVM"
    }

    init {
        Log.d(TAG, "PostVM: ready")
    }

    var posts : MediatorLiveData<BaseResource<List<ResponsePost>>>? = null
    fun observePosts(): LiveData<BaseResource<List<ResponsePost>>>? {
        val func = "observePosts+"
        myLogD(TAG, func)

        if (posts == null) {
            posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()
            posts?.value = BaseResource.loading()
            val source: LiveData<BaseResource<List<ResponsePost>>> =
                LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPotsFromUserRx1(sessionManager.userId?.toInt()!!)
                        .onErrorReturn{
                            Log.d(TAG, "apply: ", it)
                            val responsePost = ResponsePost()
                            responsePost.id = -1
                            val p: ArrayList<ResponsePost> = ArrayList<ResponsePost>()
                            p.add(responsePost)
                            p
                        }
                        .map(object :
                            Function<List<ResponsePost>, BaseResource<List<ResponsePost>>> {
                            override fun apply(t: List<ResponsePost>): BaseResource<List<ResponsePost>> {
                                if (t.isNotEmpty()) {
                                    if (t[0].id == -1) {
                                        return BaseResource.error("Ada yang salah")
                                    }
                                }
                                return BaseResource.success("Success dapat data",t)
                            }
                        })
                        .subscribeOn(Schedulers.io())
                )
            posts?.addSource(
                source
            ) { listMainResource ->
                posts?.value = listMainResource
                posts?.removeSource(
                    source
                )
            }
        }
        return posts
    }
}