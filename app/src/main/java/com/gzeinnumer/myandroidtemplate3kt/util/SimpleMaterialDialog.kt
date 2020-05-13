package com.gzeinnumer.myandroidtemplate3kt.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog
import com.gzeinnumer.myandroidtemplate3kt.R

class SimpleMaterialDialog(val context: Context) {

    private val TAG = ""

    fun dialogInfo(): MaterialDialog.Builder {
        val func = "dialogInfo+"
        myLogD(TAG,func)

        return MaterialDialog.Builder(context)
            .title("Sukses")
            .positiveText("Ok")
            .iconRes(R.drawable.mygzn_ic_warning_info_blue)
            .titleColorRes(R.color.blue_700)
            .contentColorRes(android.R.color.black)
            .buttonsGravity(GravityEnum.CENTER)
            .positiveColor(ContextCompat.getColor(context, R.color.blue_700))
    }

    fun dialogSuccess(): MaterialDialog.Builder {
        val func = "dialogSuccess+"
        myLogD(TAG,func)

        return MaterialDialog.Builder(context)
            .title("Sukses")
            .positiveText("Ok")
            .iconRes(R.drawable.mygzn_ic_warning_check_circle_green)
            .titleColorRes(R.color.green_500)
            .contentColorRes(android.R.color.black)
            .buttonsGravity(GravityEnum.CENTER)
            .positiveColor(ContextCompat.getColor(context, R.color.green_500))
    }

    fun dialogError(): MaterialDialog.Builder {
        val func = "dialogError+"
        myLogD(TAG,func)

        return MaterialDialog.Builder(context)
            .title("Warning")
            .positiveText("Ok")
            .iconRes(R.drawable.mygzn_ic_warning_cancel_red)
            .titleColorRes(R.color.red_500)
            .contentColorRes(android.R.color.black)
            .buttonsGravity(GravityEnum.CENTER)
            .negativeColor(ContextCompat.getColor(context, R.color.green_500))
    }

    fun dialogWarning(): MaterialDialog.Builder {
        val func = "dialogWarning+"
        myLogD(TAG,func)

        return MaterialDialog.Builder(context)
            .title("Warning")
            .positiveText("Ok")
            .negativeText(context.getString(R.string.cancel))
            .iconRes(R.drawable.mygzn_ic_warning_error_yellow)
            .titleColorRes(R.color.red_500)
            .contentColorRes(android.R.color.black)
            .buttonsGravity(GravityEnum.CENTER)
            .negativeColor(ContextCompat.getColor(context, R.color.green_500))
    }

}