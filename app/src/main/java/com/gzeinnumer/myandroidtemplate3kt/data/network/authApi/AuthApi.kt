package com.gzeinnumer.myandroidtemplate3kt.data.network.authApi

import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    //default-retrofit
    //https://jsonplaceholder.typicode.com/users/1
    @GET("/users/{id}")
    fun getUserCall(@Path("id") id: Int): Call<ResponseLogin>

    //rx-java-type-1
    //https://jsonplaceholder.typicode.com/users/1
    @GET("/users/{id}")
    fun getUserRx1(@Path("id") id: Int): Flowable<Response<ResponseLogin>>

    //rx-java-type-2
    //https://jsonplaceholder.typicode.com/users/1
    @GET("/users/{id}")
    fun getUserRx2(@Path("id") id: Int): Observable<Response<ResponseLogin>>

    //coroutines
    @GET("/users/{id}")
    fun getUserCoroutines(@Path("id") id: Int): Deferred<Response<ResponseLogin>>


}