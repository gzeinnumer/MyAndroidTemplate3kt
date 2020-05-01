package com.gzeinnumer.myandroidtemplate3kt.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val func = "onCreate+"
        myLogD(AppDatabase.TAG,func)

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthVM::class.java)

        initOnClick()

        subcribeObservers()
    }

    private fun initOnClick() {
        val func = "initOnClick+"
        myLogD(TAG,func)

        binding.loginButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.userIdInput.text.toString())) {
                return@setOnClickListener
            }
            viewModel.authWithId(binding.userIdInput.text.toString().toInt())
        }
    }

    private fun subcribeObservers() {
        val func = "subcribeObservers+"
        myLogD(TAG,func)

        viewModel.stateUser().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                        onHideLoading()
                        onShowSucces(it.message)
                        onSuccessLogin()
                    }
                    BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                        onHideLoading()
                        onShowError(it.message)
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
    }
}
