package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.data.SessionManager
import javax.inject.Inject

class ProfileVM @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {
    private val TAG = "ProfileVM"

    init {
        Log.d(TAG, "ProfileVM: ready...")
    }
}