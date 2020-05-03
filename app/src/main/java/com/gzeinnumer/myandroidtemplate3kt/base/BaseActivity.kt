package com.gzeinnumer.myandroidtemplate3kt.base

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.util.SimpleMaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerAppCompatActivity
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty

abstract class BaseActivity : DaggerAppCompatActivity(){

    private val TAG = "BaseActivity"

    lateinit var alertLoading: AlertDialog
    lateinit var builderLoading: SpotsDialog.Builder

    override fun onStart() {
        super.onStart()
        val func = "onStart+"
        myLogD(TAG,func)

        builderLoading = SpotsDialog.Builder().setContext(this).setMessage("Mohon Menunggu").setCancelable(false)

        alertLoading = builderLoading.build()

    }

    protected open fun onTransision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val func = "onCreate+"
        myLogD(TAG,func)
    }

    fun onSuccess(msg: String?) {
        val func = "onSuccess+"
        myLogD(TAG,func)

        msg?.let { Toasty.success(this, it, Toast.LENGTH_SHORT).show() }
    }

    fun onFailed(msg: String?) {
        val func = "onFailed+"
        myLogD(TAG,func)

        msg?.let { Toasty.error(this, it, Toast.LENGTH_LONG).show() }
    }

    fun onHideLoading() {
        val func = "onHideLoading+"
        myLogD(TAG,func)

        alertLoading.apply {
            dismiss()
        }
    }

    fun onShowLoading() {
        val func = "onShowLoading+"
        myLogD(TAG,func)

        alertLoading.apply {
            show()
        }
    }

    fun onShowWarning(msg: String?) {
        val func = "onShowWarning+"
        myLogD(TAG,func)

        SimpleMaterialDialog(this).dialogWarning()
            .content(getString(R.string.warning_back))
            .negativeText(getString(R.string.cancel))
            .onPositive { _, _ ->

            }
            .onNegative { _, _ ->

            }
            .show()
    }

    fun onShowError(msg: String?) {
        val func = "onShowError+"
        myLogD(TAG,func)

        msg?.let {
            SimpleMaterialDialog(this).dialogError()
                .content(it)
                .onPositive { _, _ ->

                }
                .onNegative { _, _ ->

                }
                .show()
        }
    }

    fun onShowSucces(msg: String?) {
        val func = "onShowSucces+"
        myLogD(TAG,func)

        msg?.let {
            SimpleMaterialDialog(this).dialogSuccess()
                .content(it)
                .onPositive { _, _ ->

                }
                .onNegative { _, _ ->

                }
                .show()
        }
    }

    fun onShowInfo(msg: String?) {
        val func = "onShowInfo+"
        myLogD(TAG,func)

        msg?.let {
            SimpleMaterialDialog(this).dialogInfo()
                .content(it)
                .onPositive { _, _ ->

                }
                .onNegative { _, _ ->

                }
                .show()
        }
    }

    override fun onDestroy() {
        val func = "onDestroy+"
        myLogD(TAG,func)

        super.onDestroy()
        finish()
    }
}