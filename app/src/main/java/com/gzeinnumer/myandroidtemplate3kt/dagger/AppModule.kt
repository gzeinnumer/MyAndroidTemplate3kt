package com.gzeinnumer.myandroidtemplate3kt.dagger

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.FORMAT_yyyy_MM_DD_HH_mm_ss
import com.gzeinnumer.myandroidtemplate3kt.util.NetworkAvailable
import com.gzeinnumer.myandroidtemplate3kt.util.TokenInterceptor
import com.gzeinnumer.myandroidtemplate3kt.util.URL_PATH
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun providesSessionManager(app: Application): SessionManager {
        return SessionManager(app)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(sessionManager: SessionManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(sessionManager))
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder()
        .serializeNulls()
        .setDateFormat(FORMAT_yyyy_MM_DD_HH_mm_ss)
        .disableHtmlEscaping()
        .create()
    
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(URL_PATH)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun providesNetworkAvailable(app: Application): NetworkAvailable = NetworkAvailable(app)

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providesDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.kotlin)!!
    }

    @Singleton
    @Provides
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.mygzn_ic_broken)
            .error(R.drawable.mygzn_ic_broken).circleCrop()
    }

    @Singleton
    @Provides
    fun providesRequestManager(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }
}