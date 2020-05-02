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
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.NetworkAvailable
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostVM @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi,
    private val db: AppDatabase,
    private val networkAvailable: NetworkAvailable
) : ViewModel() {

    companion object {
        private const val TAG = "PostVM"
    }

    init {
        Log.d(TAG, "PostVM: ready")
    }

    var posts: MediatorLiveData<BaseResource<List<ResponsePost>>>? =
        MediatorLiveData<BaseResource<List<ResponsePost>>>()

    fun observePosts(isLoadNew: Boolean): MediatorLiveData<BaseResource<List<ResponsePost>>>? {
        val func = "observePosts+"
        myLogD(TAG, func)

        posts?.value = BaseResource.loading()
        if (!isLoadNew && db.storeResponsePostDao().getRowCount() == 0) {
            myLogD(TAG, func + "Load from api")
            loadDataPostFromServer()
        } else if (!isLoadNew && db.storeResponsePostDao().getRowCount() > 0) {
            myLogD(TAG, func + "Load from room")
            val data: List<ResponsePost> = db.storeResponsePostDao().getAll()
            posts?.setValue(BaseResource.success("Success dapat data ", data))
        } else if (isLoadNew) {
            myLogD(TAG, func + "Load new data from api")
            loadDataPostFromServer()
        }
        return posts
    }

    private fun loadDataPostFromServer() {
        if (networkAvailable.isNetworkAvailable()) {
            val source: LiveData<BaseResource<List<ResponsePost>>> =
                LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPotsFromUserRx1(sessionManager.userId?.toInt()!!)
                        .onErrorReturn {
                            Log.d(TAG, "apply: ", it)
                            val responsePost = ResponsePost()
                            responsePost.id = -1
                            val p: ArrayList<ResponsePost> = ArrayList<ResponsePost>()
                            p.add(responsePost)
                            p
                        }
                        .map(object :
                            Function<List<ResponsePost>, BaseResource<List<ResponsePost>>> {
                            override fun apply(listResponsePost: List<ResponsePost>): BaseResource<List<ResponsePost>> {
                                if (listResponsePost.isNotEmpty()) {
                                    if (listResponsePost[0].id == -1) {
                                        return BaseResource.error("Ada yang salah")
                                    }
                                }
                                db.storeResponsePostDao().insertAll(listResponsePost)
                                return BaseResource.success("Success dapat data", listResponsePost)
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
        } else {
            posts?.setValue(BaseResource.error("Hubungkan ke internet"))
        }
    }
}