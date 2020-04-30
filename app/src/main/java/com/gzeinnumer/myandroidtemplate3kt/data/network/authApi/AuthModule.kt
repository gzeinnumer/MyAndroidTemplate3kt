package com.gzeinnumer.myandroidtemplate3kt.data.network.authApi

import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {

    @AuthScope
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}