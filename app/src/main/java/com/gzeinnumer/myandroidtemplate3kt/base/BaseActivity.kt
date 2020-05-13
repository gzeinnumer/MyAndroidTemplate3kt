package com.gzeinnumer.myandroidtemplate3kt.base

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.util.SimpleMaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerAppCompatActivity
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty
import java.util.ArrayList

abstract class BaseActivity : DaggerAppCompatActivity(){

    private val TAG = "BaseActivity"

    lateinit var alertLoading: AlertDialog
    lateinit var builderLoading: SpotsDialog.Builder

    var permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_NETWORK_STATE)

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

        if (checkPermissions()) {
            allGrated()
        }
    }

    private fun allGrated() {
        Toast.makeText(this, "All Granted", Toast.LENGTH_SHORT).show()
    }

    var MULTIPLE_PERMISSIONS = 1
    private fun checkPermissions(): Boolean {
        var result: Int
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(applicationContext, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                allGrated()
            } else {
                val perStr = StringBuilder()
                for (per in permissions) {
                    perStr.append("\n").append(per)
                }
            }
        }
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