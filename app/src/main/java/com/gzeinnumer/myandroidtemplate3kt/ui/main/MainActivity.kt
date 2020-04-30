package com.gzeinnumer.myandroidtemplate3kt.ui.main

import android.os.Bundle
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.base.BaseActivity
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLogD(TAG, "onCreate : ")
    }
}
