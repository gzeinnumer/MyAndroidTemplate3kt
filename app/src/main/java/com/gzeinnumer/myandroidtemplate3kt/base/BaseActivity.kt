package com.gzeinnumer.myandroidtemplate3kt.base

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
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

    fun onShowWarning(msg: String?): MaterialDialog.Builder {
        val func = "onShowWarning+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).onNegative((dialog, which) -> {}).show()
        return SimpleMaterialDialog(this).dialogWarning().content(getString(R.string.warning_back))
    }

    fun onShowError(msg: String): MaterialDialog.Builder {
        val func = "onShowError+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(this).dialogError().content(msg)
    }

    fun onShowSucces(msg: String): MaterialDialog.Builder {
        val func = "onShowSucces+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(this).dialogSuccess().content(msg)
    }

    fun onShowInfo(msg: String): MaterialDialog.Builder {
        val func = "onShowInfo+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(this).dialogInfo().content(msg)
    }

    override fun onDestroy() {
        val func = "onDestroy+"
        myLogD(TAG,func)

        super.onDestroy()
        finish()
    }
}