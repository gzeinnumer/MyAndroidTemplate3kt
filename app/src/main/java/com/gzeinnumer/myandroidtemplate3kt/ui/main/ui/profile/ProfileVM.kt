package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import javax.inject.Inject

class ProfileVM @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    private val TAG = "ProfileVM"

    init {
        Log.d(TAG, "ProfileVM: ready...")
    }

    private var stateUser: MediatorLiveData<ResponseLogin>? = null

    fun getUserData(): LiveData<ResponseLogin>? {
        if (stateUser == null) {
            stateUser = MediatorLiveData<ResponseLogin>()
            val responseLogin = ResponseLogin()
            responseLogin.apply {
                id = sessionManager.userId?.toInt() ?: -1
                name = sessionManager.name
                username = sessionManager.username
                email = sessionManager.email
                website = sessionManager.website
            }
            stateUser?.value = responseLogin
        }
        return stateUser
    }
}