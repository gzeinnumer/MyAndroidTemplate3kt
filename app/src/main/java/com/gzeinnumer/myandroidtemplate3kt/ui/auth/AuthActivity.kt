package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.myandroidtemplate3kt.base.BaseActivity
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelProviderFactory
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.databinding.ActivityAuthBinding
import com.gzeinnumer.myandroidtemplate3kt.ui.main.MainActivity
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    private val TAG = "AuthActivity"

    lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: AuthVM

    override fun onStart() {
        super.onStart()
        val func = "onStart+"
        myLogD(TAG, func)

        viewModel.cekSession().observe(this, Observer {
            if (it) {
                onSuccessLogin()
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val func = "onCreate+"
        myLogD(AppDatabase.TAG,func)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthVM::class.java)

        initOnClick()

        subcribeObserversRx1()
    }

    private fun initOnClick() {
        val func = "initOnClick+"
        myLogD(TAG,func)

        binding.loginButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.userIdInput.text.toString())) {
                return@setOnClickListener
            }
//            subcribeObserversCall(binding.userIdInput.text.toString().toInt())
            viewModel.authWithIdRx1(binding.userIdInput.text.toString().toInt())
//            subcribeObserversRx2(binding.userIdInput.text.toString().toInt())
        }
    }

    private fun subcribeObserversCall(userId: Int) {
        val func = "subcribeObserversCall+"
        myLogD(TAG,func)

        viewModel.authWithIdCall(userId).observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                        onHideLoading()
                        onSuccess(it.message)
                        onSuccessLogin()
                    }
                    BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                        onHideLoading()
                        it.message?.let { it1 -> onShowError(it1).show() }
                    }
                    BaseResource.BaseResourceStatus.STATUS_6_LOADING -> onShowLoading()
                }
            }
        })
    }

    private fun subcribeObserversRx1() {
        val func = "subcribeObserversRx1+"
        myLogD(TAG,func)

        viewModel.stateUserRx1().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                        onHideLoading()
                        onSuccess(it.message)
                        onSuccessLogin()
                    }
                    BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                        onHideLoading()
                        it.message?.let { it1 -> onShowError(it1).show() }
                    }
                    BaseResource.BaseResourceStatus.STATUS_6_LOADING -> onShowLoading()
                }
            }
        })
    }

    private fun subcribeObserversRx2(userId: Int) {
        val func = "subcribeObserversRx2+"
        myLogD(TAG,func)

        viewModel.authWithIdRx2(userId).observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                        onHideLoading()
                        onSuccess(it.message)
                        onSuccessLogin()
                    }
                    BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                        onHideLoading()
                        it.message?.let { it1 -> onShowError(it1).show() }
                    }
                    BaseResource.BaseResourceStatus.STATUS_6_LOADING -> onShowLoading()
                }
            }
        })
    }

    private fun onSuccessLogin() {
        val func = "onSuccessLogin+"
        myLogD(TAG,func)

        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
        onTransision()
    }
}
