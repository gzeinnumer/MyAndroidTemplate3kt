package com.gzeinnumer.myandroidtemplate3kt.data.network.authApi

import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("/users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<ResponseLogin>
}