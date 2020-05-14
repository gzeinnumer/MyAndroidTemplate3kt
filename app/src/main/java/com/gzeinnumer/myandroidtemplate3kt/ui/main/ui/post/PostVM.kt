package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.NetworkAvailable
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
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

    fun observePostsCall(isLoadNew: Boolean) : LiveData<BaseResource<List<ResponsePost>>> {
        val func = "observePostsCall+"
        myLogD(TAG, func)

        return repository.getPostFromUserCall(sessionManager.userId?.toInt()!!, isLoadNew)
    }

    var posts: MediatorLiveData<BaseResource<List<ResponsePost>>>? = MediatorLiveData<BaseResource<List<ResponsePost>>>()
    fun observePostsRx1(isLoadNew: Boolean): MediatorLiveData<BaseResource<List<ResponsePost>>>? {
        val func = "observePostsRx1+"
        myLogD(TAG, func)

        posts?.value = BaseResource.loading()
        if ((!isLoadNew && db.storeResponsePostDao().getRowCount() == 0) ||isLoadNew) {
            myLogD(TAG, func + "Load from api")
            loadDataPostFromServerRx1()
        } else if (!isLoadNew && db.storeResponsePostDao().getRowCount() > 0) {
            myLogD(TAG, func + "Load from room")
            val data: List<ResponsePost> = db.storeResponsePostDao().getAll()
            posts?.value = BaseResource.success("Success dapat data local", data)
        }
        return posts
    }

    private fun loadDataPostFromServerRx1() {
        val func = "loadDataPostFromServerRx1+"
        myLogD(TAG, func)

        if (networkAvailable.isNetworkAvailable()) {
            val source: LiveData<BaseResource<List<ResponsePost>>> =
                LiveDataReactiveStreams.fromPublisher(
                    repository.getPotsFromUserRx1(sessionManager.userId?.toInt())
                )
            posts?.addSource(source) { listMainResource ->
                posts?.value = listMainResource
                posts?.removeSource(source)
            }
        } else {
            posts?.setValue(BaseResource.error("Hubungkan ke internet"))
        }
    }

    fun observePostsRx2(isLoadNew: Boolean): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "observePostsRx2+"
        myLogD(TAG, func)

        return repository.getPotsFromUserRx2(sessionManager.userId?.toInt(), isLoadNew)
    }

    fun observePostsCoroutines(isLoadNew: Boolean): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "observePostsRx2+"
        myLogD(TAG, func)

        return repository.getPotsFromUserCoroutines(sessionManager.userId?.toInt(), isLoadNew)
    }
}