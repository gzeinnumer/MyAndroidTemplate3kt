package com.gzeinnumer.myandroidtemplate3kt.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.util.SimpleMaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerFragment
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty

abstract class BaseFragment : DaggerFragment() {

    lateinit var alertLoading: AlertDialog
    lateinit var builderLoading: SpotsDialog.Builder

    override fun onStart() {
        super.onStart()
        val func = "onStart+"
        myLogD(TAG, func)
        builderLoading =
            SpotsDialog.Builder().setContext(requireContext()).setMessage("Mohon Menunggu")
                .setCancelable(false)
        alertLoading = builderLoading.build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG, func)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        val func = "onViewCreated+"
        myLogD(TAG, func)
    }

    protected fun onSuccess(msg: String?) {
        val func = "onSuccess+"
        myLogD(TAG, func)
        msg?.let { Toasty.success(requireContext(), it, Toasty.LENGTH_SHORT).show() }
    }

    protected fun onFailed(msg: String?) {
        val func = "onFailed+"
        myLogD(TAG, func)
        msg?.let { Toasty.error(requireContext(), it, Toasty.LENGTH_SHORT).show() }
    }

    protected fun onHideLoading() {
        val func = "onHideLoading+"
        myLogD(TAG, func)
        alertLoading.dismiss()
    }

    protected fun onShowLoading() {
        val func = "onShowLoading+"
        myLogD(TAG, func)
        alertLoading.show()
    }
    fun onShowWarning(msg: String?): MaterialDialog.Builder {
        val func = "onShowWarning+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).onNegative((dialog, which) -> {}).show()
        return SimpleMaterialDialog(requireContext()).dialogWarning().content(getString(R.string.warning_back))
    }

    fun onShowError(msg: String): MaterialDialog.Builder {
        val func = "onShowError+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(requireContext()).dialogError().content(msg)
    }

    fun onShowSucces(msg: String): MaterialDialog.Builder {
        val func = "onShowSucces+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(requireContext()).dialogSuccess().content(msg)
    }

    fun onShowInfo(msg: String): MaterialDialog.Builder {
        val func = "onShowInfo+"
        myLogD(TAG,func)

        //.onPositive((dialog, which) -> { }).show()
        return SimpleMaterialDialog(requireContext()).dialogInfo().content(msg)
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}