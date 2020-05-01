package com.gzeinnumer.myandroidtemplate3kt

import com.gzeinnumer.myandroidtemplate3kt.dagger.DaggerAppComponent
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApp : DaggerApplication() {

    val TAG = "MyApp"

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val func = "applicationInjector+"
        myLogD(TAG,func)

        return DaggerAppComponent.builder().application(this).build()
    }
}