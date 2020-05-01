package com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi

import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    //rx-java-type-1
    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPotsFromUserRx1(
        @Query("userId") id: Int
    ): Flowable<List<ResponsePost>>

    //rx-java-type-2
    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPotsFromUserRx2(
        @Query("userId") id: Int
    ): Observable<List<ResponsePost>>

    //coroutines
    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("/posts")
    fun getPotsFromUserCoroutines(@Query("userId") id: Int): Deferred<List<ResponsePost>>

    //default-retrofit
    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPotsFromUser(
        @Query("userId") id: Int
    ): Call<List<ResponsePost>>
}