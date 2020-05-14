package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

class AuthVM @Inject constructor(
    private val repository: AuthRepositoryImpl,
    private val sessionManager: SessionManager
) : ViewModel() {

    val TAG = "AuthVM"

    init {
        myLogD(TAG, "AuthVM: viewmodel sudah bekerja")
    }

    fun authWithIdCall(userId: Int): LiveData<BaseResource<ResponseLogin>>  {
        val func = "authWithIdCall+"
        myLogD(TAG, func)

        return repository.authWithIdCall(userId)
    }

    private val authUserRx1 = MediatorLiveData<BaseResource<ResponseLogin>>()

    fun stateUserRx1(): MediatorLiveData<BaseResource<ResponseLogin>> {
        val func = "stateUserRx1+"
        myLogD(TAG, func)

        return authUserRx1
    }

    fun authWithIdRx1(userId: Int) {
        val func = "authWithIdRx1+"
        myLogD(TAG, func)

        authUserRx1.value = BaseResource.loading()
        val source: LiveData<BaseResource<ResponseLogin>> = LiveDataReactiveStreams.fromPublisher(
            repository.authWithIdRx1(userId)
        )
        authUserRx1.addSource(source) { responseLoginBaseResource ->
            authUserRx1.value = responseLoginBaseResource
            authUserRx1.removeSource(source)
        }
    }

    fun authWithIdRx2(userId: Int): LiveData<BaseResource<ResponseLogin>> {
        val func = "authWithIdRx2+"
        myLogD(TAG, func)

        return repository.authWithIdRx2(userId)
    }

    fun authWithIdCoroutines(userId: Int): LiveData<BaseResource<ResponseLogin>> {
        val func = "authWithIdCoroutines+"
        myLogD(TAG, func)

        return repository.authWithIdCoroutines(userId)
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