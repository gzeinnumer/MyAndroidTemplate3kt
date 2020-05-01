package com.gzeinnumer.myandroidtemplate3kt.dagger.main

import com.gzeinnumer.myandroidtemplate3kt.data.network.mainApi.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {

    @MainScope
    @Provides
    fun providesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}