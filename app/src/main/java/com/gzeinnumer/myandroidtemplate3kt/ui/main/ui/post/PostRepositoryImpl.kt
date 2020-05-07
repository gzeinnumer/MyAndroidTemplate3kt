package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.util.Log
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi.MainApi
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
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

}