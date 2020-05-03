package com.gzeinnumer.myandroidtemplate3kt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

class MainVM @Inject constructor(private val sessionManager: SessionManager): ViewModel(){

    companion object {
        private const val TAG = "MainVM"
    }

    init {
        myLogD(TAG, "MainVM: viewmodel sudah bekerja")
    }

    fun logout() {
        sessionManager.destroy()
    }

    private val stateSession = MediatorLiveData<Boolean>()

    fun cekSession(): LiveData<Boolean> {
        val func = "cekSession+" + sessionManager.userId
        myLogD(TAG, func)
        if (sessionManager.userId.equals("")) {
            stateSession.setValue(false)
        } else {
            stateSession.setValue(true)
        }
        return stateSession
    }
}