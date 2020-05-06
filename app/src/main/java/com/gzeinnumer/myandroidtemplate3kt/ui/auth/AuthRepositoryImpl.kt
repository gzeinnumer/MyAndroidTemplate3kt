package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthScope
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import com.gzeinnumer.myandroidtemplate3kt.data.network.authApi.AuthApi
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AuthScope
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    val sessionManager: SessionManager
) : AuthRepository {

    private val TAG = "AuthRepositoryImplement"

    init {
        myLogD(TAG, "AuthRepositoryImplementation:  sudah bekerja")
    }

    override fun authWithId(userId: Int): Flowable<BaseResource<ResponseLogin>> {
        val func = "authWithId+$userId"
        myLogD(TAG, func)

        return authApi.getUserRx1(userId)
            .onErrorReturn {
                myLogD(TAG, it.message.toString())
                val responseLogin = ResponseLogin()
                responseLogin.id = -1
                responseLogin
            }
            .map(object : Function<ResponseLogin, BaseResource<ResponseLogin>> {
                override fun apply(responseLogin: ResponseLogin): BaseResource<ResponseLogin> {
                    if (responseLogin.id == -1) {
                        return BaseResource.error("Gagal login")
                    }
                    sessionManager.setAuth(responseLogin)
                    return BaseResource.success("Success login", responseLogin)
                }
            })
            .subscribeOn(Schedulers.io())
    }

}