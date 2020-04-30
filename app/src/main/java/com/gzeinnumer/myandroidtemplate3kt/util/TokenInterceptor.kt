package com.gzeinnumer.myandroidtemplate3kt.util

import android.content.Context
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import okhttp3.Interceptor
import java.io.IOException

class TokenInterceptor(private val session: SessionManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = arrayOf(chain.request())

        if (session.token != null) {
            request[0] = request[0].newBuilder()
                    .addHeader("Authorization", "Bearer " + session.token!!)
                    .build()
        }

        return chain.proceed(request[0])
    }
}