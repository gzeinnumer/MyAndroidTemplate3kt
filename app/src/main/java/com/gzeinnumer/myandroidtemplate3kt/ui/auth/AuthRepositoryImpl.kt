package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gzeinnumer.myandroidtemplate3kt.base.BaseHttpCode
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthScope
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin
import com.gzeinnumer.myandroidtemplate3kt.data.network.authApi.AuthApi
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    @SuppressLint("CheckResult")
    override fun authWithIdCall(userId: Int): LiveData<BaseResource<ResponseLogin>> {
        val func = "authWithIdCall+$userId"
        myLogD(TAG, func)

        val data = MutableLiveData<BaseResource<ResponseLogin>>()
        data.value = BaseResource.loading()

        authApi.getUserCall(userId).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin?>, response: Response<ResponseLogin>) {
                if (response.code() == BaseHttpCode.HTTP_1_SUCCESS) {
                    response.body()?.let {
                        sessionManager.setAuth(it)
                        data.value = BaseResource.success("Success login", it)
                    }
                } else {
                    data.setValue(BaseResource.error("Tidak bisa login"))
                }
            }

            override fun onFailure(call: Call<ResponseLogin?>, t: Throwable) {
                data.value = BaseResource.error("Tidak bisa login")
            }
        })

        return data
    }
    override fun authWithIdRx1(userId: Int): Flowable<BaseResource<ResponseLogin>> {
        val func = "authWithIdRx1+$userId"
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

    @SuppressLint("CheckResult")
    override fun authWithIdRx2(userId: Int): MutableLiveData<BaseResource<ResponseLogin>> {
        val func = "authWithIdRx2+$userId"
        myLogD(TAG, func)

        val data = MutableLiveData<BaseResource<ResponseLogin>>()
        data.value = BaseResource.loading()

        authApi.getUserRx2(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                sessionManager.setAuth(it)
                data.value = BaseResource.success("Success login", it)
            }, {
                data.value = BaseResource.error("Gagal Login")
            })

        return data
    }

}