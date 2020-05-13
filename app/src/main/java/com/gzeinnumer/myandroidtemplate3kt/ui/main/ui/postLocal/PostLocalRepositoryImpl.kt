package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@MainScope
class PostLocalRepositoryImpl @Inject constructor(val db: AppDatabase) : PostLocalRepository {

    private val TAG = "PostLocalRepositoryImpl"

    init {
        myLogD(TAG, "PostLocalRepositoryImpl: ready")
    }

    override fun getList(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getList+"
        myLogD(TAG, func)

        val posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()

        posts.value = BaseResource.loading()
        val data: List<ResponsePost> = db.storeResponsePostDao().getAll()

        if (data.isNotEmpty()) {
            myLogD(TAG, func + "Jumlah data " + data.size)
            posts.setValue(BaseResource.success("Success dapat data local", data))
        } else {
            myLogD(TAG, func + "Jumlah data " + data.size)
            posts.setValue(BaseResource.error("Tidak ada data dalam local"))
        }
        return posts
    }

    @SuppressLint("CheckResult")
    override fun getListFLowable(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListFLowable+"
        myLogD(TAG, func)

        val posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()

        posts.value = BaseResource.loading()

        db.storeResponsePostDao().getAllFlowable()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ value ->
                if (value.isNotEmpty()) {
                    posts.setValue(BaseResource.success("Success dapat data local", value))
                } else {
                    posts.setValue(BaseResource.error("Tidak ada data dalam local"))
                }
            }, { e -> posts.setValue(BaseResource.error("Gagal dapat data : " + e.message)) })

        return posts;
    }

    @SuppressLint("CheckResult")
    override fun getListMaybe(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListMaybe+"
        myLogD(TAG, func)

        val posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()

        posts.value = BaseResource.loading()

        db.storeResponsePostDao().getAllMaybe()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                value ->
                if (value.isNotEmpty()) {
                    posts.setValue(BaseResource.success("Success dapat data local", value))
                } else {
                    posts.setValue(BaseResource.error("Tidak ada data dalam local"))
                }
            }, {e -> posts.setValue(BaseResource.error("Gagal dapat data : " + e.message))})
        return posts;
    }

    @SuppressLint("CheckResult")
    override fun getListSingle(): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getListSingle+"
        myLogD(TAG, func)

        val posts = MediatorLiveData<BaseResource<List<ResponsePost>>>()

        posts.value = BaseResource.loading()

        db.storeResponsePostDao().getAllSingle()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ value ->
                if (value.isNotEmpty()) {
                    posts.setValue(BaseResource.success("Success dapat data local", value))
                } else {
                    posts.setValue(BaseResource.error("Tidak ada data dalam local"))
                }
            }, { e -> posts.setValue(BaseResource.error("Gagal dapat data : " + e.message)) })

        return posts
    }
}