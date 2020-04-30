package com.gzeinnumer.myandroidtemplate3kt.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.net.InetAddress
import java.net.UnknownHostException

class NetworkAvailable(private val context: Context) {
    private val TAG = "NetworkAvailable"
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    fun isInternetAvailable(): Boolean {
        try {
            val address = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            myLogD(TAG, "Error Connection: ${e.message}")
        }

        return false
    }
}