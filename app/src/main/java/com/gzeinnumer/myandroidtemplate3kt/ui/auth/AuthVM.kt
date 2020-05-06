package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import com.gzeinnumer.myandroidtemplate3kt.data.network.authApi.AuthApi
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthVM @Inject constructor(
    private val repository: AuthRepositoryImpl,
    private val sessionManager: SessionManager
) : ViewModel() {

    val TAG = "AuthVM"

    init {
        myLogD(TAG, "AuthVM: viewmodel sudah bekerja")
    }

    private val authUser: MediatorLiveData<BaseResource<ResponseLogin>> =
        MediatorLiveData<BaseResource<ResponseLogin>>()

    fun stateUser(): MediatorLiveData<BaseResource<ResponseLogin>> {
        return authUser
    }

    fun authWithId(userId: Int) {
        val func = "authWithId+"
        myLogD(AppDatabase.TAG, func)

        authUser.value = BaseResource.loading()
        val source: LiveData<BaseResource<ResponseLogin>> = LiveDataReactiveStreams.fromPublisher(
            repository.authWithId(userId)
        )
        authUser.addSource(source) { responseLoginBaseResource ->
            authUser.value = responseLoginBaseResource
            authUser.removeSource(source)
        }
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