package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//ingat object yang ada didalam function yang di inject, itu sudah ada di @Provide Module
@Suppress("CAST_NEVER_SUCCEEDS", "SENSELESS_COMPARISON")
class AuthVM @Inject constructor() : ViewModel() {

    companion object {
        private const val TAG = "AuthVM"
    }


}