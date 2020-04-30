package com.gzeinnumer.myandroidtemplate3kt.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(){

    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        myLogD(TAG, "onCreate: ")
    }
}