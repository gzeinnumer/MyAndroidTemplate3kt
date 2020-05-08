package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseHttpCode
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi.MainApi
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@MainScope
class PostRepositoryImpl @Inject constructor(
    private val mainApi: MainApi,
    private val db: AppDatabase
) : PostRepository{

    val TAG = "PostRepositoryImpl"

    init {
        myLogD(TAG, "PostRepositoryImpl: ready")
    }

    override fun getPostFromUserCall(
        userId: Int,
        isLoadNew: Boolean
    ): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getPostFromUserCall+"
        myLogD(TAG, func)

        val data = MutableLiveData<BaseResource<List<ResponsePost>>>()
        data.value = BaseResource.loading()

        if ((!isLoadNew && db.storeResponsePostDao().getRowCount() == 0) || isLoadNew) {
            myLogD(TAG, func + "Load from api")
            mainApi.getPostFromUserCall(userId)
                .enqueue(object : Callback<List<ResponsePost>> {
                    override fun onResponse(
                        call: Call<List<ResponsePost>>,
                        response: Response<List<ResponsePost>>
                    ) {
                        if (response.code() == BaseHttpCode.HTTP_1_SUCCESS) {
                            response.body()?.let {
                                data.setValue(BaseResource.success("Success dapatkan data", it))
                            }
                        } else {
                            data.setValue(BaseResource.error("Gagal mendapatkan data"))
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponsePost>>,
                        t: Throwable
                    ) {
                        data.value = BaseResource.error("Gagal mendapatkan data")
                    }
                })
        } else if (!isLoadNew && db.storeResponsePostDao().getRowCount() > 0) {
            myLogD(TAG, func + "Load from room")
            val d = db.storeResponsePostDao().getAll()
            data.value = BaseResource.success("Success dapat data ", d)
        }
        return data

    }

    override fun getPotsFromUserRx1(userId: Int): Flowable<BaseResource<List<ResponsePost>>> {
        val func = "getPotsFromUserRx1+"
        myLogD(TAG, func)

        return mainApi.getPostFromUserRx1(userId)
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
    }

    override fun getPotsFromUserRx2(
        userId: String?,
        isLoadNew: Boolean
    ): LiveData<BaseResource<List<ResponsePost>>> {
        val func = "getPotsFromUserRx2+"
        myLogD(TAG, func)

        val data = MutableLiveData<BaseResource<List<ResponsePost>>>()
        data.value = BaseResource.loading()

        if ((!isLoadNew && db.storeResponsePostDao().getRowCount() == 0) || isLoadNew) {
            myLogD(TAG, func + "Load from api")
            mainApi.getPostFromUserCall(userId!!.toInt())
                .enqueue(object : Callback<List<ResponsePost>> {
                    override fun onResponse(
                        call: Call<List<ResponsePost>>,
                        response: Response<List<ResponsePost>>
                    ) {
                        if (response.code() == BaseHttpCode.HTTP_1_SUCCESS) {
                            response.body()?.let {
                                data.setValue(BaseResource.success("Success dapatkan data", it))
                            }
                        } else {
                            data.setValue(BaseResource.error("Gagal mendapatkan data"))
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponsePost>>,
                        t: Throwable
                    ) {
                        data.value = BaseResource.error("Gagal mendapatkan data")
                    }
                })
        } else if (!isLoadNew && db.storeResponsePostDao().getRowCount() > 0) {
            myLogD(TAG, func + "Load from room")
            val d = db.storeResponsePostDao().getAll()
            data.value = BaseResource.success("Success dapat data ", d)
        }
        return data
    }

}