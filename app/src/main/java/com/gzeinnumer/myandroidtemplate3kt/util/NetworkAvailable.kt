package com.gzeinnumer.myandroidtemplate3kt.util

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import java.net.UnknownHostException

class NetworkAvailable(private val context: Context) {

    private val TAG = "NetworkAvailable"

    fun isNetworkAvailable(): Boolean {
        val func = "isNetworkAvailable+"
        myLogD(TAG,func)

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    fun isInternetAvailable(): Boolean {
        val func = "isInternetAvailable+"
        myLogD(TAG,func)

        try {
            val address = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            myLogD(TAG, "Error Connection: ${e.message}")
        }

        return false
    }
}