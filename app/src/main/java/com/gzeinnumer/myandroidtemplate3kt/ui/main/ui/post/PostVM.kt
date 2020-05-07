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
    private val repository: PostRepositoryImpl,
    private val sessionManager: SessionManager,
    private val db: AppDatabase,
    private val networkAvailable: NetworkAvailable
) : ViewModel() {

    val TAG = "PostVM"

    init {
        myLogD(TAG, "PostVM: ready")
    }

    var posts: MediatorLiveData<BaseResource<List<ResponsePost>>>? = MediatorLiveData<BaseResource<List<ResponsePost>>>()

    fun observePostsRx1(isLoadNew: Boolean): MediatorLiveData<BaseResource<List<ResponsePost>>>? {
        val func = "observePostsRx1+"
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
        val func = "loadDataPostFromServer+"
        myLogD(TAG, func)

        if (networkAvailable.isNetworkAvailable()) {
            val source: LiveData<BaseResource<List<ResponsePost>>> =
                LiveDataReactiveStreams.fromPublisher(
                    repository.getPotsFromUserRx1(sessionManager.userId?.toInt()!!)
                )
            posts?.addSource(source) { listMainResource ->
                posts?.value = listMainResource
                posts?.removeSource(source)
            }
        } else {
            posts?.setValue(BaseResource.error("Hubungkan ke internet"))
        }
    }
}