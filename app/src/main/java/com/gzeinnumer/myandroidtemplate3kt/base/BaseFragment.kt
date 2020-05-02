package com.gzeinnumer.myandroidtemplate3kt.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    protected fun onShowWarning(msg: String?) {
        val func = "onShowWarning+"
        myLogD(TAG, func)
        SimpleMaterialDialog(requireContext()).dialogWarning()
            .content(getString(R.string.warning_back)+" "+msg)
            .negativeText(getString(R.string.cancel))
            .onPositive { _, _ -> }
            .onNegative { _, _ -> }
            .show()
    }

    protected fun onShowError(msg: String?) {
        val func = "onShowError+"
        myLogD(TAG, func)
        msg?.let {
            SimpleMaterialDialog(requireContext()).dialogError()
                .content(it)
                .onPositive { _, _ -> }
                .onNegative { _, _ -> }
                .show()
        }
    }

    protected fun onShowSucces(msg: String?) {
        val func = "onShowSucces+"
        myLogD(TAG, func)
        msg?.let {
            SimpleMaterialDialog(requireContext()).dialogSuccess()
                .content(it)
                .onPositive { _, _ -> }
                .onNegative { _, _ -> }
                .show()
        }
    }

    protected fun onShowInfo(msg: String?) {
        val func = "onShowInfo+"
        myLogD(TAG, func)
        msg?.let {
            SimpleMaterialDialog(requireContext()).dialogInfo()
                .content(it)
                .onPositive { _, _ -> }
                .onNegative { _, _ -> }
                .show()
        }
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}