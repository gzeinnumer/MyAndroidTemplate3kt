package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import androidx.lifecycle.*
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import com.gzeinnumer.myandroidtemplate3kt.data.network.authApi.AuthApi
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthVM @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthVM"
    }

    init {
        myLogD(TAG, "AuthVM: viewmodel sudah bekerja")
    }

    private val authUser: MediatorLiveData<BaseResource<ResponseLogin>> = MediatorLiveData<BaseResource<ResponseLogin>>()

    fun stateUser(): MediatorLiveData<BaseResource<ResponseLogin>> {
        return authUser
    }

    fun authWithId(userId: Int) {
        myLogD(TAG, "authWithId: attempting to login")
        authUser.value = BaseResource.loading()
        val source: LiveData<BaseResource<ResponseLogin>> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUserRx1(userId)
                .onErrorReturn {
                    myLogD(TAG, it.message.toString())
                    val responseLogin = ResponseLogin()
                    responseLogin.id = -1
                    responseLogin
                }
                .map(object : Function<ResponseLogin, BaseResource<ResponseLogin>>{
                    override fun apply(responseLogin: ResponseLogin): BaseResource<ResponseLogin> {
                        if(responseLogin.id == -1){
                            return BaseResource.error("Gagal login")
                        }
                        return BaseResource.success("Success login",responseLogin)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source) { responseLoginBaseResource->
            authUser.value = responseLoginBaseResource
            authUser.removeSource(source)
        }
    }

}