package com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi

import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPotsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<ResponsePost>>
}