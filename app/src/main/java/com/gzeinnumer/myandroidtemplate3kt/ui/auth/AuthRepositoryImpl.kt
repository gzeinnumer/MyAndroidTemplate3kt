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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
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
                    data.setValue(BaseResource.error("Tidak bisa login : "+response.code()))
                }
            }

            override fun onFailure(call: Call<ResponseLogin?>, t: Throwable) {
                data.value = BaseResource.error("Tidak bisa login : "+t.message)
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
                null
            }
            .map(object : Function<Response<ResponseLogin>, BaseResource<ResponseLogin>> {
                @Suppress("SENSELESS_COMPARISON")
                override fun apply(responseLoginResponse: Response<ResponseLogin>): BaseResource<ResponseLogin> {
                    if (responseLoginResponse === null) {
                        return BaseResource.error("Gagal login")
                    } else {
                        if(responseLoginResponse.code() == BaseHttpCode.HTTP_1_SUCCESS){
                            responseLoginResponse.body()?.let {
                                sessionManager.setAuth(it)
                                return BaseResource.success("Success login", it)
                            }
                        }
                        return BaseResource.error("Gagal login : " + responseLoginResponse.code())
                    }
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
                if(it.code() == BaseHttpCode.HTTP_1_SUCCESS){
                    it.body()?.let {
                        it1 -> sessionManager.setAuth(it1)
                        data.value = BaseResource.success("Success login", it1)
                    }
                } else {
                    data.value = BaseResource.error("Gagal Login :  " + it.code())
                }

            }, {
                data.value = BaseResource.error("Gagal Login : "+it.message)
            })

        return data
    }

    @SuppressLint("CheckResult")
    override fun authWithIdCoroutines(userId: Int): MutableLiveData<BaseResource<ResponseLogin>> {
        val func = "authWithIdCoroutines+$userId"
        myLogD(TAG, func)

        val data = MutableLiveData<BaseResource<ResponseLogin>>()
        data.value = BaseResource.loading()

        GlobalScope.launch(Dispatchers.Main) {
            try{
                val call = userId.let { authApi.getUserCoroutines(it) }
                val response = call.await()
                when(response.code()){
                    BaseHttpCode.HTTP_1_SUCCESS->{
                        response.body()?.let {
                            data.value = BaseResource.success("Success Dapat data online", it)
                        }
                    }
                    else ->{
                        data.value = BaseResource.error("Gagal dapatkan data :  " + response.code())
                    }
                }
            } catch (e: Exception){
                data.value = BaseResource.error("Gagal Dapatkan data "+ e.message)
            }
        }

        return data
    }

}